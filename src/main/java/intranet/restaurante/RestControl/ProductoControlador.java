package intranet.restaurante.RestControl;

import intranet.restaurante.Entidades.Producto;
import intranet.restaurante.Entidades.Categoria;
import intranet.restaurante.Servicios.ProductoService;
import intranet.restaurante.Servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoControlador {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(producto.getCategoria().getIdCategoria());
        producto.setCategoria(categoria);
        return productoService.crearProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(producto.getCategoria().getIdCategoria());
        producto.setCategoria(categoria);
        producto.setIdProducto(id);
        return productoService.actualizarProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
    }
}