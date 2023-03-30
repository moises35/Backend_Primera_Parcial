package py.com.prueba.laboratorio.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Cliente;

@Stateless
public class BolsaDaoImpl implements BolsaDao{
    @PersistenceContext(unitName="pruebaPU")
        EntityManager em;

    @Override
    public List<Bolsa> findAllBolsas() {
        //ordenar por fecha de caducidad
        return em.createNamedQuery("Bolsa.findAllSortAsc").getResultList();
    }

    @Override
    public Bolsa findBolsaById(Bolsa bolsa) {
      return em.find(Bolsa.class, bolsa.getIdBolsa());
    }

    @Override
    public void insertBolsa(Bolsa bolsa) {
        em.persist(bolsa);
    }

    @Override
    public void updateBolsa(Bolsa bolsa) {
       em.merge(bolsa);
    }

    @Override
    public void deleteBolsa(Bolsa bolsa) {
       em.remove(em.merge(bolsa));
    }

    @Override
    public void updateBolsaSaldoCaducados() {
        //si la bolsa ya vencio se pone saldo = 0
        String update = "UPDATE Bolsa b SET b.saldoPuntos = 0 WHERE b.fechaCaducidad < :fecha";
        Query q = em.createQuery(update);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        //Para que deje usar los puntos hasta el final del día inclusive
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        
        q.setParameter("fecha", cal.getTime());
        q.executeUpdate();
        
    }

    @Override
    public List<Bolsa> findAllBolsasRangoFecha(Date inicio, Date fin) {
        String rango = "SELECT b FROM Bolsa b where b.fechaAsignacion between :fechaInicio and :fechaFin";
        Query q = em.createQuery(rango, Bolsa.class);
        q.setParameter("fechaInicio", inicio);
        q.setParameter("fechaFin", fin);
        return q.getResultList();
    }

    @Override
    public List<Bolsa> findBolsaByCliente(Cliente cliente) {
        String sql = "SELECT b FROM Bolsa b WHERE b.idCliente.idCliente = :cliente";
        Query q = em.createQuery(sql, Bolsa.class);
        q.setParameter("cliente", cliente.getIdCliente());
        try{
            return (List<Bolsa>) q.getResultList();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<Cliente> findClienteVnecmientoBolsa(Integer dias) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dias);
        Date fechaCaducidad = cal.getTime();
        String sql = "SELECT c FROM Cliente c, Bolsa b "
                + "WHERE c.idCliente = b.idCliente.idCliente "
                + "AND b.fechaCaducidad < :fechaCaducidad ";
               
        Query q = em.createQuery(sql, Cliente.class);
        q.setParameter("fechaCaducidad", fechaCaducidad);
        try{
            return (List<Cliente>) q.getResultList();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<Bolsa> findAllBolsasRangoPunto(Integer punto1, Integer punto2) {
        String slq = "SELECT b FROM Bolsa b WHERE b.saldoPuntos BETWEEN :punto1 AND :punto2";
        Query q = em.createQuery(slq, Bolsa.class);
        q.setParameter("punto1", punto1);
        q.setParameter("punto2", punto2);
        try{
            return (List<Bolsa>) q.getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
}
