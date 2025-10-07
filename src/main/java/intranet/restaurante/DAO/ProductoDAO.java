package intranet.restaurante.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import intranet.restaurante.Entidades.Producto;

public interface ProductoDAO extends JpaRepository <Producto, Integer>{

}