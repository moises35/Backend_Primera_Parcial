package py.com.prueba.laboratorio.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Cabecera;
import py.com.prueba.laboratorio.modelo.Cliente;
import py.com.prueba.laboratorio.modelo.Concepto;
import py.com.prueba.laboratorio.modelo.Detalle;
import py.com.prueba.laboratorio.utils.Mail;


@Path("/usoPuntos")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class UsoPuntosServiceRS {
    
    @Inject
    private UsoPuntosService usoPuntosService;
    
    @GET
    /* mostrar todos los detalles y cabecera */
    public List<Detalle> listarUsoPuntos() {
        
        return usoPuntosService.listarDetalle();
        
    }
    
    /* listar por concepto de uso */
    @GET
    @Path("/concepto")
    public List<Detalle> listarPorConceptoDeUso(Concepto concepto){
        return usoPuntosService.listarDetallePorConcepto(concepto);
    }
    
    @GET
    @Path("/cliente")
    public List<Detalle> listarPorCliente(Cliente cliente){
        return usoPuntosService.listarDetallePorCliente(cliente);
    }
    
    /* lista por fecha */
    
    @GET
    @Path("/porFecha")
    public List<Detalle> listarPorFecha(@QueryParam("fecha") String fecha) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");
        Date date = formatter.parse(fecha);
        
        return usoPuntosService.listarDetallePorFecha(date);
        
        
        
    }

    @POST
    public Response usarPuntos(Cabecera cabecera) {
        Detalle detalle = null;
        
        try {
            Integer idCliente = cabecera.getIdCliente().getIdCliente();
            Integer idConcepto = cabecera.getConceptoDeUso().getIdConcepto();
            System.out.println("--> El idClientes es: " + idCliente);
            System.out.println("--> El idConcepto es: " + idConcepto);
            
            /*buscar bolsas del cliente */
            List<Bolsa> bolsa = usoPuntosService.buscarBolsaCliente(idCliente);
            /* buscar concepto */
            Concepto concepto = usoPuntosService.buscarConcepto(idConcepto);
            System.out.println(bolsa);
            System.out.println(concepto);
            if (bolsa != null) /* verificar si cumple con el punto requerido del concepto*/ {
                System.out.println(" *** Entre al primer if  ***");
                Bolsa bolsa1 = usoPuntosService.verificarPuntoRequerido(concepto, bolsa);
                if (bolsa1 != null) {
                    System.out.println(" *** Entre al segundo if  ***");
                    bolsa1.setPuntajeUtilizado(concepto.getPuntosRequeridos());
                    cabecera.setPuntosUtilizado(concepto.getPuntosRequeridos());
                    Integer saldo = bolsa1.getPuntajeAsignado() - bolsa1.getPuntajeUtilizado();
                    bolsa1.setSaldoPuntos(saldo);
                    usoPuntosService.actualizarBolsa(bolsa1);
                    cabecera.setFecha(new Date()); //se guarda la fecha en que se uso el punto
                    cabecera = usoPuntosService.guardarCabecera(cabecera);
                    /*guardar detalle*/
                    detalle = new Detalle();
                    detalle.setIdCabecera(cabecera);
                    detalle.setIdBolsa(bolsa1);
                    detalle.setPuntajeUtilizado(concepto.getPuntosRequeridos());
                    
                    usoPuntosService.guardarDetalle(detalle);
                    Mail.sendEmail("destinatario@example.com", "Prueba de correo electrónico", "¡Hola! Este es un mensaje de prueba.");
                }
                
            }
            
            return Response.ok().entity(detalle).build();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            
        }
        
    }
}
