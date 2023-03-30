package py.com.prueba.laboratorio.rest;

import java.util.List;
import javax.ejb.Remote;
import py.com.prueba.laboratorio.modelo.Concepto;

@Remote
public interface ConceptoServiceRemote {
    public List<Concepto> listarConceptos();
    public Concepto encontrarConceptoPorId(Concepto concepto);
    public void registrarConcepto(Concepto concepto);
    public void modificarConcepto(Concepto concepto);
    public void eliminarConcepto(Concepto concepto);
}
