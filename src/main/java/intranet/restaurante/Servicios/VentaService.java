package intranet.restaurante.Servicios;

import intranet.restaurante.DTO.VentaRequest;
import intranet.restaurante.Entidades.Venta;

import java.util.List;

public interface VentaService {
    Venta registrarVenta(VentaRequest request);
    Venta obtenerPorId(Integer id);
    List<Venta> listarTodas();
}
