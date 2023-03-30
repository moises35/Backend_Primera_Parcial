package py.com.prueba.laboratorio.ejb;

import java.util.List;
import py.com.prueba.laboratorio.modelo.ReglaAsignacion;

public interface ReglaAsignacionDao {
    public List<ReglaAsignacion> findAllReglas();
    public ReglaAsignacion findReglaById(ReglaAsignacion regla);
    public Integer findReglaByMonto(Integer monto);
    public void insertRegla(ReglaAsignacion regla);
    public void updateRegla(ReglaAsignacion regla);
    public void deleteRegla(ReglaAsignacion regla);
}
