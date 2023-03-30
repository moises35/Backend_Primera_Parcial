package py.com.prueba.laboratorio.rest;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Cabecera;
import py.com.prueba.laboratorio.modelo.Cliente;
import py.com.prueba.laboratorio.modelo.Concepto;
import py.com.prueba.laboratorio.modelo.Detalle;

@Local
public interface UsoPuntosService {
    public List<Detalle> listarDetalle();
    public List<Detalle> listarDetallePorConcepto(Concepto concepto);
    public List<Detalle> listarDetallePorCliente(Cliente cliente);
    public List<Detalle> listarDetallePorFecha(Date fecha);
    public void guardarDetalle(Detalle detalle);
    public Cabecera guardarCabecera(Cabecera cabecera);
    public List<Bolsa> buscarBolsaCliente(Integer idCliente);
    public Concepto buscarConcepto(Integer idConcepto);
    public Bolsa verificarPuntoRequerido(Concepto concepto, List<Bolsa> bolsa);
    public void actualizarBolsa(Bolsa bolsa);
}
