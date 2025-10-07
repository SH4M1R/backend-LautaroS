package intranet.restaurante.ServiciosImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import intranet.restaurante.DAO.CategoriaDAO;
import intranet.restaurante.Entidades.Categoria;
import intranet.restaurante.Servicios.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Override
    public List<Categoria> listarCategorias() { return categoriaDAO.findAll();}

    @Override
    public Categoria obtenerCategoriaPorId (Integer idCategoria) { return categoriaDAO.findById(idCategoria).get();}

    @Override
    public Categoria crearCategoria(Categoria categoria) { return categoriaDAO.save(categoria);}

    @Override
    public Categoria actualizarCategoria(Categoria categoria) { return categoriaDAO.save(categoria);}

    @Override
    public void eliminarCategoria(Integer idCategoria) { categoriaDAO.deleteById(idCategoria); }
}