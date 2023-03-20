package py.com.progweb.backend.rest;

import javax.enterprise.context.RequestScoped;
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
import py.com.progweb.backend.ejb.PremioDAO;
import py.com.progweb.backend.model.Premio;

@Path("premio")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PremioRest {
    @Inject
    PremioDAO premioDao;
    
    // Para probar si funciona
    @GET
    @Path("/")
    public Response hola() {
        return Response.ok("Accediste a la ruta de premio").build();
    }

    @POST
    @Path("/add")
    public Response addNewClient(Premio entidad) {
        premioDao.agregar(entidad);
        return Response.ok(entidad).build();
    }

    @GET
    @Path("/all")
    public Response getAll() {
        return Response.ok(premioDao.listarTodos()).build();
    }
    
    @GET
    @Path("/find/{id}")
    public Response getById(@PathParam("id") Integer id) {
        return Response.ok(premioDao.obtenerID(id)).build();
    }
    
    @PUT
    @Path("/update")
    public Response update(Premio entidad) {
        return Response.ok(premioDao.actualizar(entidad)).build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return Response.ok(premioDao.eliminar(id)).build();
    }
}
