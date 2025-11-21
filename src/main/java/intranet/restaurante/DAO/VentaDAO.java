package intranet.restaurante.DAO;

import intranet.restaurante.Entidades.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaDAO extends JpaRepository<Venta, Integer> {
    // Aquí puedes agregar consultas específicas sobre Ventas si quieres
}
