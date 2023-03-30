package py.com.prueba.laboratorio.ejb;

import java.util.Date;
import java.util.List;
import py.com.prueba.laboratorio.modelo.Vencimiento;

public interface VencimientoDao {
    public List<Vencimiento> findAllVencimientos();
    public Integer findVencimientoByDuracion(Date fecha);
    public Vencimiento findVencimientoById(Vencimiento vencimiento);
    public void insertVencimiento(Vencimiento vencimiento);
    public void updateVencimiento(Vencimiento vencimiento);
    public void deleteVencimiento(Vencimiento vencimiento);
}
