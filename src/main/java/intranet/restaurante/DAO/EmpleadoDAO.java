package intranet.restaurante.DAO;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import intranet.restaurante.Entidades.Empleado;

public interface EmpleadoDAO extends JpaRepository <Empleado, Integer>{
    Optional<Empleado> findByUsername(String username);
}