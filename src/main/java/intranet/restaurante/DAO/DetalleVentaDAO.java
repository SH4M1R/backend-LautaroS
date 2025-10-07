package intranet.restaurante.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import intranet.restaurante.Entidades.DetalleVenta;

public interface DetalleVentaDAO extends JpaRepository <DetalleVenta, Integer>{

}