package py.com.prueba.laboratorio.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Cliente;
import py.com.prueba.laboratorio.modelo.Concepto;
import py.com.prueba.laboratorio.modelo.Detalle;


@Stateless
public class DetalleDaoImpl implements DetalleDao{
    @PersistenceContext(unitName="pruebaPU")
        EntityManager em;

    @Override
    public List<Detalle> findAllDetalle() {
        return em.createNamedQuery("Detalle.findAll", Detalle.class).getResultList();
    }

    @Override
    public List<Detalle> findAllByIdConcepto(Concepto concepto){
        String sql = "SELECT d FROM Detalle d "
                + "WHERE d.idCabecera.conceptoDeUso.idConcepto = :idConceptoDeUso";
        Query q = em.createQuery(sql, Detalle.class);
        q.setParameter("idConceptoDeUso", concepto.getIdConcepto());
        try{
            return (List<Detalle>) q.getResultList();
            
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<Detalle> findAllByIdBolsa(Bolsa bolsa) {
        String sql = "SELECT d FROM detalle d WHERE d.idBolsa = :idBolsa";
        Query q = em.createQuery(sql, Detalle.class);
        q.setParameter("idBolsa", bolsa.getIdBolsa());
        try{
            return (List<Detalle>) q.getResultList();
            
        }catch(NoResultException e){
            return null;
        }
        
    }

    @Override
    public void insertDetalle(Detalle detalle) {
        em.persist(detalle);
    }
    

    @Override
    public void updateDetalla(Detalle detalle) {
        em.merge(detalle);
    }

    @Override
    public void deteleDetalle(Detalle detalle) {
        em.remove(em.merge(detalle));
    }

    @Override
    public List<Detalle> findAllByIdCliente(Cliente cliente) {
        String sql = "SELECT d FROM Detalle d "
                + "WHERE d.idCabecera.idCliente.idCliente = :idCliente";
        Query q = em.createQuery(sql, Detalle.class);
        q.setParameter("idCliente", cliente.getIdCliente());
        try{
            return (List<Detalle>) q.getResultList();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<Detalle> findAllByIdFecha(Date fecha) {
        String sql = "SELECT d FROM Detalle d WHERE d.idCabecera.fecha = :fecha";
        Query q = em.createQuery(sql, Detalle.class);
        q.setParameter("fecha", fecha);
        try{
            return (List<Detalle>) q.getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
}
