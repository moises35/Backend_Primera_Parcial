package py.com.progweb.backend.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.progweb.backend.model.Premio;

@Stateless
public class PremioDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;
    
    // Create
    public void agregar(Premio entidad) {
        this.em.persist(entidad);
    }

    // Read
    public List<Premio> listarTodos(){
        return this.em.createQuery("select p from Premio p").getResultList();
    }
    
    public Premio obtenerID(Integer id) {
        return this.em.find(Premio.class, id);
    }
    
    // Update
    public Premio actualizar(Premio entidad) {
        return this.em.merge(entidad);
    }
    
    // Eliminar
    public String eliminar(Integer premioID) {
        this.em.remove(this.obtenerID(premioID));
        return "Eliminación exitosa";
    }
}
