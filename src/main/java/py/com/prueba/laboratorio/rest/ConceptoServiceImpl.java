
package py.com.prueba.laboratorio.rest;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import py.com.prueba.laboratorio.ejb.ConceptoDao;
import py.com.prueba.laboratorio.modelo.Concepto;


@Stateless
public class ConceptoServiceImpl implements  ConceptoServiceRemote, ConceptoService {
    
    @Inject
    private ConceptoDao conceptoDao;

    @Resource
    private SessionContext contexto;

    @Override
    public List<Concepto> listarConceptos() {
        return conceptoDao.findAllConceptos();
    }

    @Override
    public Concepto encontrarConceptoPorId(Concepto concepto) {
        return conceptoDao.findConceptoById(concepto);
    }

    @Override
    public void registrarConcepto(Concepto concepto) {
        conceptoDao.insertConcepto(concepto);
    }

    @Override
    public void modificarConcepto(Concepto concepto) {
        try {
            conceptoDao.updateConcepto(concepto);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);

        }
    }

    @Override
    public void eliminarConcepto(Concepto concepto) {
         conceptoDao.deleteConcepto(concepto);
    }
    
}
