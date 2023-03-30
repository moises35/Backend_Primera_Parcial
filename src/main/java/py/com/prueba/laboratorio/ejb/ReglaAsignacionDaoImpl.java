package py.com.prueba.laboratorio.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.prueba.laboratorio.modelo.ReglaAsignacion;

@Stateless
public class ReglaAsignacionDaoImpl implements ReglaAsignacionDao {

    @PersistenceContext(unitName = "pruebaPU")
    EntityManager em;

    @Override
    public List<ReglaAsignacion> findAllReglas() {
        return em.createNamedQuery("ReglaAsignacion.findAll").getResultList();
    }

    @Override
    public ReglaAsignacion findReglaById(ReglaAsignacion regla) {
        return em.find(ReglaAsignacion.class, regla.getIdReglaAsignacion());
    }

    @Override
    public void insertRegla(ReglaAsignacion regla) {
        em.persist(regla);
    }

    @Override
    public void updateRegla(ReglaAsignacion regla) {
        em.merge(regla);
    }

    @Override
    public void deleteRegla(ReglaAsignacion regla) {
        em.remove(em.merge(regla));
    }

    @Override
    public Integer findReglaByMonto(Integer monto) {
        String sql = "SELECT r FROM ReglaAsignacion r WHERE :monto BETWEEN r.limiteInferior"
                + " AND r.limiteSuperior ";
        Query q = em.createQuery(sql, ReglaAsignacion.class);
        q.setParameter("monto", monto);

        try {
            ReglaAsignacion temp = (ReglaAsignacion) q.getSingleResult();
            return (Integer) Math.round(monto / temp.getMontoEquivalencia());
        } catch (NoResultException e) {
            return null;
        }
    }
}
