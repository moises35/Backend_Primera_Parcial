package py.com.prueba.laboratorio.ejb;

import java.util.List;
import py.com.prueba.laboratorio.modelo.Cliente;


public interface ClienteDao {
    public List<Cliente> findAllClientes();
    public Cliente findClienteById(Cliente cliente);
    public List<Cliente> findClienteByNombre(Cliente cliente);
    public List<Cliente> findClienteByApellido(Cliente cliente);
    public List<Cliente> FindClienteByFechaNac(Cliente cliente);
    public void insertCliente(Cliente cliente);
    public void updateCliente(Cliente cliente);
    public void deleteCliente(Cliente cliente);
}
