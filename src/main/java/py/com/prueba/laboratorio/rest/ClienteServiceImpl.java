package py.com.prueba.laboratorio.rest;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import py.com.prueba.laboratorio.ejb.ClienteDao;
import py.com.prueba.laboratorio.modelo.Cliente;

@Stateless
public class ClienteServiceImpl implements  ClienteServiceRemote, ClienteService{
    @Inject
    private ClienteDao clienteDao;

    @Resource
    private SessionContext contexto;

    @Override
    public List<Cliente> listarClientes() {
        return clienteDao.findAllClientes();
    }

    @Override
    public Cliente encontrarClientePorId(Cliente cliente) {
        return clienteDao.findClienteById(cliente);
    }

    @Override
    public void registrarCliente(Cliente cliente) {
        clienteDao.insertCliente(cliente);
    }

    @Override
    public void modificarCliente(Cliente cliente) {
         try {
            clienteDao.updateCliente(cliente);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);

        }
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteDao.deleteCliente(cliente);
    }

    @Override
    public List<Cliente> encontrarClientePorNombre(Cliente cliente) {
        return clienteDao.findClienteByNombre(cliente);
    }

    @Override
    public List<Cliente> encontrarClientePorApellido(Cliente cliente) {
        return clienteDao.findClienteByApellido(cliente);
    }

    @Override
    public List<Cliente> encontrarClientePorFechaNac(Cliente cliente) {
        return clienteDao.FindClienteByFechaNac(cliente);
    }
    
}
