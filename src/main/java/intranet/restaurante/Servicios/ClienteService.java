package intranet.restaurante.Servicios;

import intranet.restaurante.Entidades.Cliente;
import java.util.List;

public interface ClienteService {
List<Cliente> listarClientes();
    Cliente crearCliente(Cliente cliente);
    Cliente obtenerClientePorId(Integer idCliente);
    Cliente actualizarCliente(Cliente cliente);
    void eliminarCliente(Integer idCliente);
}
