package py.com.prueba.laboratorio.ejb;

import java.util.List;
import py.com.prueba.laboratorio.modelo.Cabecera;
import py.com.prueba.laboratorio.modelo.Cliente;
import py.com.prueba.laboratorio.modelo.Concepto;

public interface CabeceraDao {
    public List<Cabecera> findAllCabeceras();
    public Cabecera insertCabecera(Cabecera cabecera);
    public void updateCabecera(Cabecera cabecera);
    public List<Cabecera> findCabeceraByIdCliente(Cliente cliente);
    public List<Cabecera> findCabeceraByIdConcepto(Concepto concepto);
    public void deleteCabecera(Cabecera cabecera);
}
