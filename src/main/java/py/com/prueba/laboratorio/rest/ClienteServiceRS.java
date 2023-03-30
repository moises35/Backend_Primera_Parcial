package py.com.prueba.laboratorio.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import py.com.prueba.laboratorio.modelo.Cliente;

@Path("/clientes")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class ClienteServiceRS {
    
    @Inject
    private ClienteService clienteService;
    
    @GET
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }
    
    @GET
    @Path("{id}")//hace referencia a /clientes/{id}
    public Cliente encontrarClientePorId(@PathParam("id") int id) {
        return clienteService.encontrarClientePorId(new Cliente(id));
    }
    
    @POST
    public Response agregarCliente(Cliente cliente) {
        try {
            clienteService.registrarCliente(cliente);
            return Response.ok().entity(cliente).build();
        } catch (Exception e) {

            e.printStackTrace(System.out);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();

        }
    }
    
    @PUT
    @Path("{id}")
    public Response modificarCliente(@PathParam("id") int id, Cliente clienteModificado) {

        try {
            Cliente cliente = clienteService.encontrarClientePorId(new Cliente(id));
            if (cliente != null) {
                clienteService.modificarCliente(clienteModificado);
                return Response.ok().entity(clienteModificado).build();
            } else {
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

    }
    
    @DELETE
    @Path("{id}")
    public Response eliminarClientePorId(@PathParam("id") int id) {
        try {
            clienteService.eliminarCliente(new Cliente(id));
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(404).build();
        }
    }
    
    @GET
    @Path("/consultaNombre")
    public List<Cliente> encontrarClientePorNombre(@QueryParam("nombre") String nombre){
        Cliente c = new Cliente();
        c.setNombre(nombre);
        return clienteService.encontrarClientePorNombre(c);
    }
    
    @GET
    @Path("/consultaApellido")
    public List<Cliente> encontrarClientePorApellido(@QueryParam("apellido") String apellido){
        Cliente c = new Cliente();
        c.setApellido(apellido);
        return clienteService.encontrarClientePorApellido(c);
    }
    
    @GET
    @Path("/consultaCumple")
    public List<Cliente> encontrarClientePorFechaNac(@QueryParam("fecha") String fecha) throws ParseException{
        Cliente c = new Cliente();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");
        Date date = formatter.parse(fecha);
        
        System.out.println(date);
        
        c.setFechaNacimiento(date);
        return clienteService.encontrarClientePorFechaNac(c);
    }
    
}
