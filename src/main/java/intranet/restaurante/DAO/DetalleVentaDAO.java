package intranet.restaurante.DAO;

import intranet.restaurante.DTO.PlatoVendidoDTO;
import intranet.restaurante.Entidades.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaDAO extends JpaRepository<DetalleVenta, Integer> {

    @Query("SELECT new intranet.restaurante.DTO.PlatoVendidoDTO(d.producto.producto, SUM(d.cantidad)) " +
           "FROM DetalleVenta d " +
           "GROUP BY d.producto.producto " +
           "ORDER BY SUM(d.cantidad) DESC")
    List<PlatoVendidoDTO> findPlatosMasVendidos();
}
