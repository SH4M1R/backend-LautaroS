package intranet.restaurante.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import intranet.restaurante.Entidades.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Integer>{

}