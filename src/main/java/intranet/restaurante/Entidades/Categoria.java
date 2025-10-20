package intranet.restaurante.Entidades;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Entity
@Data
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    private String nombreCategoria;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnoreProperties("categoria")
    private List<Producto> productos;
}
