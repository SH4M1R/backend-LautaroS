package intranet.restaurante.Entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Integer idProducto;
    private String Producto;
    private String Descripcion;
    private BigDecimal PrecioVenta;
    private String Foto;
    private Boolean Estado;

    @ManyToOne
    @JoinColumn(name = "Categoria_idCategoria", nullable = false)
    private Categoria categoria;
}