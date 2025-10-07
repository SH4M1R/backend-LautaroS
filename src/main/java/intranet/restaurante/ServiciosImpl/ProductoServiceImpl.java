package intranet.restaurante.ServiciosImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import intranet.restaurante.DAO.ProductoDAO;
import intranet.restaurante.Entidades.Producto;
import intranet.restaurante.Servicios.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired 
    private ProductoDAO productoDAO;

    @Override
    public Producto crearProducto(Producto producto) {return productoDAO.save(producto);}   

    @Override
    public List<Producto> listarProductos() {return productoDAO.findAll();}
    
    @Override
    public Producto obtenerProductoPorId(Integer idProducto) {return productoDAO.findById(idProducto).get();}
    
    @Override
    public Producto actualizarProducto(Producto producto) {return productoDAO.save(producto);}  

    @Override
    public void eliminarProducto(Integer idProducto) {productoDAO.deleteById(idProducto);}  
}