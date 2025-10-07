package intranet.restaurante.Servicios;

import intranet.restaurante.Entidades.Categoria;
import java.util.List;

public interface CategoriaService {
    List <Categoria> listarCategorias();
    Categoria crearCategoria(Categoria categoria);
    Categoria obtenerCategoriaPorId(Integer idCategoria);
    Categoria actualizarCategoria(Categoria categoria);
    void eliminarCategoria(Integer idCategoria);
}