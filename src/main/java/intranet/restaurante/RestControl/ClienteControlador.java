package intranet.restaurante.RestControl;

import intranet.restaurante.Entidades.Cliente;
import intranet.restaurante.Servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteControlador {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {return clienteService.listarClientes();}

    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable Integer id) {return clienteService.obtenerClientePorId(id);}

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {return clienteService.crearCliente(cliente);}

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {cliente.setIdCliente(id);
        return clienteService.actualizarCliente(cliente);}

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Integer id) {clienteService.eliminarCliente(id);}
}