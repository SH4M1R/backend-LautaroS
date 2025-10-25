package intranet.restaurante.RestControl;

import intranet.restaurante.Entidades.Producto;
import intranet.restaurante.Entidades.Categoria;
import intranet.restaurante.Servicios.ProductoService;
import intranet.restaurante.Servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:9050")
public class ProductoControlador {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public Producto crearProducto(
            @RequestPart("producto") String nombreProducto,
            @RequestPart("descripcion") String descripcion,
            @RequestPart("precioVenta") BigDecimal precioVenta,
            @RequestPart("estado") Boolean estado,
            @RequestPart("categoriaId") Integer categoriaId,
            @RequestPart(value = "imagen", required = false) MultipartFile imagenFile
    ) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(categoriaId);

        Producto producto = new Producto();
        producto.setProducto(nombreProducto);
        producto.setDescripcion(descripcion);
        producto.setPrecioVenta(precioVenta);
        producto.setEstado(estado);
        producto.setCategoria(categoria);

        if (imagenFile != null && !imagenFile.isEmpty()) {
            try {
                File directorio = new File(UPLOAD_DIR);
                if (!directorio.exists()) {
                    directorio.mkdirs();
                }

                String nombreArchivo = System.currentTimeMillis() + "_" + imagenFile.getOriginalFilename();
                Path rutaArchivo = Paths.get(UPLOAD_DIR + nombreArchivo);

                Files.write(rutaArchivo, imagenFile.getBytes());

                producto.setFoto(nombreArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return productoService.crearProducto(producto);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public Producto actualizarProducto(
            @PathVariable Integer id,
            @RequestPart("producto") String nombreProducto,
            @RequestPart("descripcion") String descripcion,
            @RequestPart("precioVenta") BigDecimal precioVenta,
            @RequestPart("estado") Boolean estado,
            @RequestPart("categoriaId") Integer categoriaId,
            @RequestPart(value = "imagen", required = false) MultipartFile imagenFile
    ) {
        Producto producto = productoService.obtenerProductoPorId(id);
        Categoria categoria = categoriaService.obtenerCategoriaPorId(categoriaId);

        producto.setProducto(nombreProducto);
        producto.setDescripcion(descripcion);
        producto.setPrecioVenta(precioVenta);
        producto.setEstado(estado);
        producto.setCategoria(categoria);

        if (imagenFile != null && !imagenFile.isEmpty()) {
            try {
                File directorio = new File(UPLOAD_DIR);
                if (!directorio.exists()) {
                    directorio.mkdirs();
                }

                String nombreArchivo = System.currentTimeMillis() + "_" + imagenFile.getOriginalFilename();
                Path rutaArchivo = Paths.get(UPLOAD_DIR + nombreArchivo);

                Files.write(rutaArchivo, imagenFile.getBytes());

                producto.setFoto(nombreArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return productoService.actualizarProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
    }
}