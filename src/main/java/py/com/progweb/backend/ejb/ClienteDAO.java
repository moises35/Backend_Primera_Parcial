package py.com.progweb.backend.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.progweb.backend.model.Cliente;

@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;
    
    // Create
    public void agregar(Cliente entidad) {
        this.em.persist(entidad);
    }

    // Read
    public List<Cliente> listarTodos(){
        return this.em.createQuery("select c from Cliente c").getResultList();
    }
    
    public Cliente obtenerID(Integer id) {
        return this.em.find(Cliente.class, id);
    }
    
    // Update
    public Cliente actualizar(Cliente entidad) {
        return this.em.merge(entidad);
    }
    
    // Eliminar
    public String eliminar(Integer clienteID) {
        this.em.remove(this.obtenerID(clienteID));
        return "Eliminación exitosa";
    }
}
