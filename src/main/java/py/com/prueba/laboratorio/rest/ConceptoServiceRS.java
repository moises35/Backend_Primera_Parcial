
package py.com.prueba.laboratorio.rest;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import py.com.prueba.laboratorio.modelo.Concepto;


@Path("/conceptos")
@Consumes("application/json")
@Produces("application/json")
@Stateless
public class ConceptoServiceRS {
    @Inject
    private ConceptoService conceptoService;
    
    @GET
    public List<Concepto> listarConceptos() {
        return conceptoService.listarConceptos();
    }
    
    @GET
    @Path("{id}")//hace referencia a /conceptos/{id}
    public Concepto encontrarConceptoPorId(@PathParam("id") int id) {
        return conceptoService.encontrarConceptoPorId(new Concepto(id));
    }
    
    @POST
    public Response agregarConcepto(Concepto concepto) {
        try {
            conceptoService.registrarConcepto(concepto);
            return Response.ok().entity(concepto).build();
        } catch (Exception e) {

            e.printStackTrace(System.out);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();

        }
    }
    
    @PUT
    @Path("{id}")
    public Response modificarConcepto(@PathParam("id") int id, Concepto conceptoModificado) {

        try {
            Concepto concepto = conceptoService.encontrarConceptoPorId(new Concepto(id));
            if (concepto != null) {
                conceptoService.modificarConcepto(conceptoModificado);
                return Response.ok().entity(conceptoModificado).build();
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
    public Response eliminarConceptoPorId(@PathParam("id") int id) {
        try {
            conceptoService.eliminarConcepto(new Concepto(id));
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(404).build();
        }
    }
}
