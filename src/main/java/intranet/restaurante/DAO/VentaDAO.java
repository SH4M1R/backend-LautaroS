package intranet.restaurante.DAO;

import intranet.restaurante.Entidades.Venta;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaDAO extends JpaRepository<Venta, Integer> {
    List<Venta> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin);

}
