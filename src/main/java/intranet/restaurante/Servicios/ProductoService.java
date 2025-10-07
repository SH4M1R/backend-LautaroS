package intranet.restaurante.Servicios;

import intranet.restaurante.Entidades.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> listarProductos();
    Producto crearProducto(Producto producto);
    Producto obtenerProductoPorId(Integer idProducto);
    Producto actualizarProducto(Producto producto);
    void eliminarProducto(Integer idProducto);
}