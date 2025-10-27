package intranet.restaurante.RestControl;

import intranet.restaurante.Entidades.Producto;
import intranet.restaurante.Entidades.Categoria;
import intranet.restaurante.Servicios.ProductoService;
import intranet.restaurante.Servicios.CategoriaService;
import intranet.restaurante.Servicios.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:9050")
public class ProductoControlador {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UploadService uploadServicio;

    @GetMapping
    public List<Producto> listarProductos() { return productoService.listarProductos();}

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Integer id) { return productoService.obtenerProductoPorId(id); }

    @PostMapping("/con-imagen")
    public Producto crearProductoConImagen(
            @RequestParam("producto") String nombreProducto,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precioVenta") Double precioVenta,
            @RequestParam("estado") Boolean estado,
            @RequestParam("idCategoria") Integer idCategoria,
            @RequestParam("imagen") MultipartFile imagen) {

        try {
            Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria);
            String nombreImagen = uploadServicio.saveUpload(imagen);

            Producto nuevo = new Producto();
            nuevo.setProducto(nombreProducto);
            nuevo.setDescripcion(descripcion);
            nuevo.setPrecioVenta(BigDecimal.valueOf(precioVenta));
            nuevo.setEstado(estado);
            nuevo.setCategoria(categoria);
            nuevo.setImagen(nombreImagen);

            return productoService.crearProducto(nuevo);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al registrar el producto con imagen: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(
            @PathVariable Integer id,
            @RequestParam("producto") String nombreProducto,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precioVenta") Double precioVenta,
            @RequestParam("estado") Boolean estado,
            @RequestParam("idCategoria") Integer idCategoria,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {

        try {
            Producto producto = productoService.obtenerProductoPorId(id);
            if (producto == null) throw new RuntimeException("Producto no encontrado");

            Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria);

            producto.setProducto(nombreProducto);
            producto.setDescripcion(descripcion);
            producto.setPrecioVenta(BigDecimal.valueOf(precioVenta));
            producto.setEstado(estado);
            producto.setCategoria(categoria);

            if (imagen != null && !imagen.isEmpty()) {
                if (producto.getImagen() != null) uploadServicio.deleteUpload(producto.getImagen());
                String nuevaImagen = uploadServicio.saveUpload(imagen);
                producto.setImagen(nuevaImagen);
            }
            return productoService.actualizarProducto(producto);

        } catch (Exception e) { e.printStackTrace();
            throw new RuntimeException("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar-imagen/{id}")
    public Producto actualizarImagenProducto(
            @PathVariable Integer id,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {

        try {
            Producto producto = productoService.obtenerProductoPorId(id);
            if (producto == null) throw new RuntimeException("Producto no encontrado");

            if (imagen != null && !imagen.isEmpty()) {
                if (producto.getImagen() != null) uploadServicio.deleteUpload(producto.getImagen());
                String nuevaImagen = uploadServicio.saveUpload(imagen);
                producto.setImagen(nuevaImagen);
            }

            return productoService.actualizarProducto(producto);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar imagen: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null && producto.getImagen() != null) uploadServicio.deleteUpload(producto.getImagen());
        productoService.eliminarProducto(id);
    }
}