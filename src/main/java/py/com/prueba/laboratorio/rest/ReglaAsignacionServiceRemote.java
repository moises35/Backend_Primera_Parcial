package py.com.prueba.laboratorio.rest;

import java.util.List;
import javax.ejb.Remote;
import py.com.prueba.laboratorio.modelo.ReglaAsignacion;

@Remote
public interface ReglaAsignacionServiceRemote {
    public List<ReglaAsignacion> listarReglas();
    public ReglaAsignacion encontrarReglaPorId(ReglaAsignacion regla);
    public Integer encontrarReglaPorMonto(Integer monto);
    public void registrarRegla(ReglaAsignacion regla);
    public void modificarRegla(ReglaAsignacion regla);
    public void eliminarRegla(ReglaAsignacion regla);
}
