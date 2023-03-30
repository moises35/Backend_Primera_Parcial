package py.com.prueba.laboratorio.rest;

import java.util.List;
import javax.ejb.Remote;
import py.com.prueba.laboratorio.modelo.Cliente;

@Remote
public interface ClienteServiceRemote {
    public List<Cliente> listarClientes();
    public Cliente encontrarClientePorId(Cliente cliente);
    public List<Cliente> encontrarClientePorNombre(Cliente cliente);
    public List<Cliente> encontrarClientePorApellido(Cliente cliente);
    public List<Cliente> encontrarClientePorFechaNac(Cliente cliente);
    public void registrarCliente(Cliente cliente);
    public void modificarCliente(Cliente cliente);
    public void eliminarCliente(Cliente cliente);
}
