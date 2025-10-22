package intranet.restaurante.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import intranet.restaurante.Entidades.Empleado;
import java.util.Optional;

public interface EmpleadoDAO extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByUsername(String username);
}
