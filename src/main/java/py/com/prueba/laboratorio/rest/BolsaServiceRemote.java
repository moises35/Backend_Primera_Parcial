package py.com.prueba.laboratorio.rest;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Cliente;

@Remote
public interface BolsaServiceRemote {
    public List<Bolsa> listarBolsas();
    public List<Bolsa> listarBolsasRangoPunto(Integer punto1, Integer punto2);
    public Bolsa encontrarBolsaPorId(Bolsa bolsa);
    public List<Bolsa> encontrarBolsaPorCliente(Cliente cliente);
    public List<Cliente> encontrarClienteConBolsaPorVencer(Integer dias);
    public void registrarBolsa(Bolsa bolsa);
    public void modificarBolsa(Bolsa bolsa);
    public void modificarBolsaSaldoCducados();
    public void eliminarBolsa(Bolsa bolsa);
    public Integer puntajeAsignado(Integer monto);
    public Integer encontrarDiasDuracion(Date fechaActual);
}
