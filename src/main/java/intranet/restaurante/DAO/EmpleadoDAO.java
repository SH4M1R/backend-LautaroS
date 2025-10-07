package intranet.restaurante.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import intranet.restaurante.Entidades.Empleado;

public interface EmpleadoDAO extends JpaRepository <Empleado, Integer>{

}