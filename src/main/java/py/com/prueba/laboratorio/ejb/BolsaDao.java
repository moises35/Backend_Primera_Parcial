package py.com.prueba.laboratorio.ejb;

import java.util.Date;
import java.util.List;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Cliente;

public interface BolsaDao {
    public List<Bolsa> findAllBolsas();
    public Bolsa findBolsaById(Bolsa bolsa);
    public List<Bolsa> findBolsaByCliente(Cliente cliente);
    public List<Cliente> findClienteVnecmientoBolsa(Integer dias);
    public void insertBolsa(Bolsa bolsa);
    public void updateBolsa(Bolsa bolsa);
    public void deleteBolsa(Bolsa bolsa);
    public void updateBolsaSaldoCaducados();
    public List<Bolsa> findAllBolsasRangoFecha(Date inicio, Date fin);
    public List<Bolsa> findAllBolsasRangoPunto(Integer punto1, Integer punto2);
}
