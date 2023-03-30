package py.com.prueba.laboratorio.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Cliente;
import py.com.prueba.laboratorio.modelo.Vencimiento;


@Path("/bolsas")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class BolsaServiceRS {

    @Inject
    private BolsaService bolsaService;

    @GET
    public List<Bolsa> listarBolsas() {
        return bolsaService.listarBolsas();
    }

    @GET
    @Path("{id}")//hace referencia a /bolsas/{id}
    public Bolsa encontrarBolsaPorId(@PathParam("id") int id) {
        return bolsaService.encontrarBolsaPorId(new Bolsa(id));
    }

    @GET
    @Path("/rangoPunto") //referencia a bolsas/rangoPunto?punto1={punto1}&punto2={punto2}
    public List<Bolsa> encontrarBolsaPorRangoPunto(@QueryParam("punto1") int punto1,
                                                    @QueryParam("punto2") int punto2){
        
        return bolsaService.listarBolsasRangoPunto(punto1, punto2);
    }

    @GET
    @Path("/cliente") // hace referencia a /bolsas/cliente?id={id}
    public List<Bolsa> encontrarBolsaPorCliente(@QueryParam("id") int id) {
        return bolsaService.encontrarBolsaPorCliente(new Cliente(id));
    }
    
    @GET
    @Path("/vencimiento")
    public List<Cliente> encontrarClienteConBolsaPorVencer(Vencimiento v){
        return bolsaService.encontrarClienteConBolsaPorVencer(v.getDuracion());
    }
    

    @POST
    @Path("/agregar")
    public Response agregarBolsa(Bolsa bolsa) {
        try {

            Calendar cal = Calendar.getInstance();
            /* cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);*/
            Date fechaHoy = new Date();

            bolsa.setFechaAsignacion(fechaHoy);
            /* para la fecha de caducidad */
            Integer diasDuracion = bolsaService.encontrarDiasDuracion(fechaHoy);
            if (diasDuracion == null) {
                diasDuracion = 30; // se accina por defecto
            }
            cal.add(Calendar.DATE, diasDuracion);
            bolsa.setFechaCaducidad(cal.getTime());
            bolsa.setPuntajeAsignado(bolsaService.puntajeAsignado(bolsa.getMontoOperacion()));
            bolsa.setSaldoPuntos(bolsa.getPuntajeAsignado());
            bolsa.setPuntajeUtilizado(0);
            bolsa.setEstado(1); //cuando se crea se activa

            bolsaService.registrarBolsa(bolsa);
            return Response.ok().entity(bolsa).build();
        } catch (Exception e) {

            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        }
    }

    @PUT
    @Path("{id}")
    public Response modificarBolsa(@PathParam("id") int id, Bolsa bolsaModificado) {

        try {
            Bolsa bolsa = bolsaService.encontrarBolsaPorId(new Bolsa(id));
            if (bolsa != null) {
                bolsaService.modificarBolsa(bolsaModificado);
                return Response.ok().entity(bolsaModificado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DELETE
    @Path("{id}")
    public Response eliminarClientePorId(@PathParam("id") int id) {
        try {
            bolsaService.eliminarBolsa(new Bolsa(id));
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(404).build();
        }
    }

}
