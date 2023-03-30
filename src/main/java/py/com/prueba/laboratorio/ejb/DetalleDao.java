package py.com.prueba.laboratorio.ejb;

import java.util.Date;
import java.util.List;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Cliente;
import py.com.prueba.laboratorio.modelo.Concepto;
import py.com.prueba.laboratorio.modelo.Detalle;

public interface DetalleDao {
    public List<Detalle> findAllDetalle();
    public List<Detalle> findAllByIdConcepto(Concepto concepto);
    public List<Detalle> findAllByIdBolsa(Bolsa bolsa);
    public List<Detalle> findAllByIdCliente(Cliente cliente);
    public List<Detalle> findAllByIdFecha(Date fecha);
    public void insertDetalle(Detalle detalle);
    public void updateDetalla(Detalle detalle);
    public void deteleDetalle(Detalle detalle);
}
