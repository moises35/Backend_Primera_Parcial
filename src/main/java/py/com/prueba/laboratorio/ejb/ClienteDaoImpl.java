package py.com.prueba.laboratorio.ejb;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.prueba.laboratorio.modelo.Cliente;

@Stateless
public class ClienteDaoImpl implements ClienteDao {
    @PersistenceContext(unitName = "pruebaPU")
    EntityManager em;

    @Override
    public List<Cliente> findAllClientes() {
        return em.createNamedQuery("Cliente.findAll").getResultList();
    }

    @Override
    public Cliente findClienteById(Cliente cliente) {
        return em.find(Cliente.class, cliente.getIdCliente());
    }

    @Override
    public void insertCliente(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        em.merge(cliente);
    }

    @Override
    public void deleteCliente(Cliente cliente) {
        em.remove(em.merge(cliente));
    }

    @Override
    public List<Cliente> findClienteByNombre(Cliente cliente) {
        String sql = "SELECT c FROM Cliente c WHERE lower(c.nombre) LIKE lower(:nombre)";
        Query q = em.createQuery(sql, Cliente.class);
        q.setParameter("nombre", "%" + cliente.getNombre() + "%");
        try {
            return (List<Cliente>) q.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<Cliente> findClienteByApellido(Cliente cliente) {
        String sql = "SELECT c FROM Cliente c WHERE lower(c.apellido) LIKE lower(:apellido)";
        Query q = em.createQuery(sql, Cliente.class);
        q.setParameter("apellido", "%" + cliente.getApellido() + "%");
        try {
            return (List<Cliente>) q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Cliente> FindClienteByFechaNac(Cliente cliente) {
        String sql = "SELECT c FROM Cliente c WHERE DAY(c.fechaNacimiento) = :dia "
                + "AND MONTH(c.fechaNacimiento) = :mes";
        Query q = em.createQuery(sql, Cliente.class);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cliente.getFechaNacimiento());

        Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
        Integer mes = calendar.get(Calendar.MONTH) + 1; //enero = 0, dic = 11

        q.setParameter("dia", dia);
        q.setParameter("mes", mes);

        try {
            return (List<Cliente>) q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
