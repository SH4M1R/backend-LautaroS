package intranet.restaurante.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import intranet.restaurante.Entidades.Rol;

public interface RolDAO extends JpaRepository <Rol, Integer>{

}