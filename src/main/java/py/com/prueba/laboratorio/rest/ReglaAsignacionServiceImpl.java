package py.com.prueba.laboratorio.rest;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import py.com.prueba.laboratorio.ejb.ReglaAsignacionDao;
import py.com.prueba.laboratorio.modelo.ReglaAsignacion;

@Stateless
public class ReglaAsignacionServiceImpl implements ReglaAsignacionService, ReglaAsignacionServiceRemote {
    @Inject
    private ReglaAsignacionDao reglaDao;

    @Resource
    private SessionContext contexto;
    
    @Override
    public List<ReglaAsignacion> listarReglas() {
        return reglaDao.findAllReglas();
    }

    @Override
    public ReglaAsignacion encontrarReglaPorId(ReglaAsignacion regla) {
        return reglaDao.findReglaById(regla);
    }

    @Override
    public void registrarRegla(ReglaAsignacion regla) {
        reglaDao.insertRegla(regla);
    }

    @Override
    public void modificarRegla(ReglaAsignacion regla) {
        try {
            reglaDao.updateRegla(regla);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);

        }
    }

    @Override
    public void eliminarRegla(ReglaAsignacion regla) {
        reglaDao.deleteRegla(regla);
    }

    @Override
    public Integer encontrarReglaPorMonto(Integer monto) {
        return reglaDao.findReglaByMonto(monto);
    }
    
}
