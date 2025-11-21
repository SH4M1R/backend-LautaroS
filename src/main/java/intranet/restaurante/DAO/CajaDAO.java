package intranet.restaurante.DAO;

import intranet.restaurante.Entidades.Caja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CajaDAO extends JpaRepository<Caja, Integer> {
    Optional<Caja> findByFechaAperturaBetween(LocalDateTime inicio, LocalDateTime fin);
}
