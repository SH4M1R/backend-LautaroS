package intranet.restaurante.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import intranet.restaurante.Entidades.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

}