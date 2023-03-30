package py.com.prueba.laboratorio.ejb;

import java.util.List;
import py.com.prueba.laboratorio.modelo.Concepto;


public interface ConceptoDao {
    public List<Concepto> findAllConceptos();
    public Concepto findConceptoById(Concepto concepto);
    public void insertConcepto(Concepto concepto);
    public void updateConcepto(Concepto concepto);
    public void deleteConcepto(Concepto concepto);
}
