package intranet.restaurante.Entidades;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Integer idProducto;

    private String producto;
    private String descripcion;
    private BigDecimal precioVenta;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "Categoria_idCategoria", nullable = false)
    @JsonIgnoreProperties("productos")
    private Categoria categoria;
}
