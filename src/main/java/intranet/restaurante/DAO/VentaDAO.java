package intranet.restaurante.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import intranet.restaurante.Entidades.Venta;

public interface VentaDAO extends JpaRepository <Venta, Integer>{

}