package py.com.prueba.laboratorio.rest;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import py.com.prueba.laboratorio.modelo.ReglaAsignacion;

@Path("/reglas")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class ReglaAsignacionServiceRS {
    @Inject
    private ReglaAsignacionService reglaService;
    
    @GET
    public List<ReglaAsignacion> listarReglas() {
        return reglaService.listarReglas();
    }
    
    @GET
    @Path("{id}")//hace referencia a /regla/{id}
    public ReglaAsignacion encontrarReglaPorId(@PathParam("id") int id) {
        return reglaService.encontrarReglaPorId(new ReglaAsignacion(id));
    }
    
    @POST
    public Response agregarRegla(ReglaAsignacion regla) {
        try {
            reglaService.registrarRegla(regla);
            return Response.ok().entity(regla).build();
        } catch (Exception e) {

            e.printStackTrace(System.out);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();

        }
    }
    
    @PUT
    @Path("{id}")
    public Response modificarRegla(@PathParam("id") int id, ReglaAsignacion reglaModificado) {

        try {
            ReglaAsignacion regla = reglaService.encontrarReglaPorId(new ReglaAsignacion(id));
            if (regla != null) {
                reglaService.modificarRegla(reglaModificado);
                return Response.ok().entity(reglaModificado).build();
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
    public Response eliminarReglaPorId(@PathParam("id") int id) {
        try {
            reglaService.eliminarRegla(new ReglaAsignacion(id));
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(404).build();
        }
    }
    
    // Para consultar la equivalencia de 1 punto dado el monto
    @GET
    @Path("/equivalencia")
    public Integer montoEquivalencia(@QueryParam("monto") Integer monto){
        return reglaService.encontrarReglaPorMonto(monto);
    }
    
}
