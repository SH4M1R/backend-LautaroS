package intranet.restaurante.Servicios;

import intranet.restaurante.DTO.ClienteRequest;
import intranet.restaurante.Entidades.Cliente;

public interface ClienteService {
    Cliente obtenerOCrearCliente(ClienteRequest dto);
}
