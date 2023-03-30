package py.com.prueba.laboratorio.rest;

import java.util.List;
import javax.ejb.Local;
import py.com.prueba.laboratorio.modelo.Vencimiento;

@Local
public interface VencimientoService {
    public List<Vencimiento> listarVencimientos();
    public Vencimiento encontrarVencimientoPorId(Vencimiento vencimiento);
    public void registrarVencimiento(Vencimiento vencimiento);
    public void modificarVencimiento(Vencimiento vencimiento);
    public void eliminarVencimiento(Vencimiento vencimiento);
}
