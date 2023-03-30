package py.com.prueba.laboratorio.ejb;

import py.com.prueba.laboratorio.modelo.Persona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PersonaDAO {
    @PersistenceContext(unitName = "pruebaPU")
    EntityManager em;

    public void agregar(Persona entidad) {
        this.em.persist(entidad);
    }

    public void modificar(Persona entidad) {
        this.em.merge(entidad);
    }

    public void eliminar(Integer id) {
        this.em.remove(this.em.find(Persona.class,id));
    }

    @SuppressWarnings("unchecked")
    public List<Persona> lista() {
        Query q = em.createQuery("SELECT p FROM Persona p");
        return (List<Persona>)q.getResultList();
    }
}
