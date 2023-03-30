package py.com.prueba.laboratorio.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.prueba.laboratorio.modelo.Cabecera;
import py.com.prueba.laboratorio.modelo.Cliente;
import py.com.prueba.laboratorio.modelo.Concepto;

@Stateless
public class CabeceraDaoImpl implements CabeceraDao{
    @PersistenceContext(unitName="pruebaPU")
    EntityManager em;

    @Override
    public List<Cabecera> findAllCabeceras() {
        return em.createNamedQuery("Cabecera.findAll", Cabecera.class).getResultList();
    }

    @Override
    public Cabecera insertCabecera(Cabecera cabecera) {
        em.persist(cabecera);
        return cabecera;
    }

    @Override
    public void updateCabecera(Cabecera cabecera) {
        em.merge(cabecera);
    }

    @Override
    public List<Cabecera> findCabeceraByIdCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cabecera> findCabeceraByIdConcepto(Concepto concepto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCabecera(Cabecera cabecera) {
        em.remove(em.merge(cabecera));
    }
} 
