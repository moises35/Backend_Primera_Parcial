package py.com.prueba.laboratorio.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.prueba.laboratorio.modelo.Concepto;

@Stateless
public class ConceptoDaoImpl implements ConceptoDao {
    @PersistenceContext(unitName="pruebaPU")
        EntityManager em;

    @Override
    public List<Concepto> findAllConceptos() {
        return em.createNamedQuery("Concepto.findAll").getResultList();
    }

    @Override
    public Concepto findConceptoById(Concepto concepto) {
        return em.find(Concepto.class, concepto.getIdConcepto());
    }

    @Override
    public void insertConcepto(Concepto concepto) {
        em.persist(concepto);

    }

    @Override
    public void updateConcepto(Concepto concepto) {
        em.merge(concepto);
    }

    @Override
    public void deleteConcepto(Concepto concepto) {
        em.remove(em.merge(concepto));
    }
}
