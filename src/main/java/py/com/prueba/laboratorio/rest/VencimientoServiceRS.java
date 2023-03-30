package py.com.prueba.laboratorio.rest;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import py.com.prueba.laboratorio.modelo.Vencimiento;


@Path("/vencimientos")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class VencimientoServiceRS {
    @Inject
    private VencimientoService vencimientoService;
    
    @GET
    public List<Vencimiento> listarVencimientos() {
        return vencimientoService.listarVencimientos();
    }
    
    @GET
    @Path("{id}")//hace referencia a /vencimientos/{id}
    public Vencimiento encontrarVencimientoPorId(@PathParam("id") int id) {
        return vencimientoService.encontrarVencimientoPorId(new Vencimiento(id));
    }
    
    @POST
    public Response agregarVencimiento(Vencimiento vencimiento) {
        try {
            vencimientoService.registrarVencimiento(vencimiento);
            
            return Response.ok().entity(vencimiento).build();
        } catch (Exception e) {

            e.printStackTrace(System.out);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        }
    }
    
    @PUT
    @Path("{id}")
    public Response modificarVencimiento(@PathParam("id") int id, Vencimiento vencimientoModificado) {

        try {
            Vencimiento vencimiento = vencimientoService.encontrarVencimientoPorId(new Vencimiento(id));
            if (vencimiento != null) {
                vencimientoService.modificarVencimiento(vencimientoModificado);
                return Response.ok().entity(vencimientoModificado).build();
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
    public Response eliminarVencimientoPorId(@PathParam("id") int id) {
        try {
            vencimientoService.eliminarVencimiento(new Vencimiento(id));
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(404).build();
        }
    }
    
}
