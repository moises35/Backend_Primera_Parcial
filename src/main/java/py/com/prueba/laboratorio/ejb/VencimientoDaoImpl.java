package py.com.prueba.laboratorio.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import py.com.prueba.laboratorio.modelo.Vencimiento;

@Stateless
public class VencimientoDaoImpl implements VencimientoDao {
    @PersistenceContext(unitName = "pruebaPU")
    EntityManager em;

    @Override
    public List<Vencimiento> findAllVencimientos() {
        return em.createNamedQuery("Vencimiento.findAll").getResultList();
    }

    @Override
    public Vencimiento findVencimientoById(Vencimiento vencimiento) {
        return em.find(Vencimiento.class, vencimiento.getIdVencimiento());
    }

    @Override
    public void insertVencimiento(Vencimiento vencimiento) {
        em.persist(vencimiento);
    }

    @Override
    public void updateVencimiento(Vencimiento vencimiento) {
        em.merge(vencimiento);
    }

    @Override
    public void deleteVencimiento(Vencimiento vencimiento) {
        em.remove(em.merge(vencimiento));
    }

    @Override
    public Integer findVencimientoByDuracion(Date fecha) {

        String sql = "SELECT MAX(v.duracion) FROM Vencimiento v WHERE :fecha BETWEEN v.fechaInicio "
                + "AND v.fechaFin";
        Query q = em.createQuery(sql, Integer.class);
        q.setParameter("fecha", fecha, TemporalType.DATE);
        
        try{
            return (Integer) q.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }

}
