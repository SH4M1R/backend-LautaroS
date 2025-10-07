package intranet.restaurante.ServiciosImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import intranet.restaurante.DAO.ClienteDAO;
import intranet.restaurante.Entidades.Cliente;
import intranet.restaurante.Servicios.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired 
    private ClienteDAO clienteDAO;

    @Override
    public List<Cliente> listarClientes() { return clienteDAO.findAll();}

    @Override
    public Cliente crearCliente(Cliente cliente) { return clienteDAO.save(cliente);}

    @Override
    public Cliente obtenerClientePorId(Integer idCliente) { return clienteDAO.findById(idCliente).get();}

    @Override
    public Cliente actualizarCliente(Cliente cliente) { return clienteDAO.save(cliente);}   

    @Override
    public void eliminarCliente(Integer idCliente) { clienteDAO.deleteById(idCliente);}

}