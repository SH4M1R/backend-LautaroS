package intranet.restaurante.RestControl;

import intranet.restaurante.Entidades.Categoria;
import intranet.restaurante.Servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:9050")
public class CategoriaControlador {
@Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public Categoria obtenerCategoria(@PathVariable Integer id) {
        return categoriaService.obtenerCategoriaPorId(id);
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.crearCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setIdCategoria(id);
        return categoriaService.actualizarCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
    }
}
