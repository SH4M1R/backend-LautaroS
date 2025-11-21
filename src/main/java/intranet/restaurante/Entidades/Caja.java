package intranet.restaurante.Entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "Caja")
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // coincide con tu tabla actual
    private Integer idCaja;

    @Column(name = "monto_inicial")
    private BigDecimal montoInicial;

    @Column(name = "total_ventas")
    private BigDecimal totalVentas;

    @Column(name = "fecha_apertura")
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @OneToMany(mappedBy = "caja")
    private List<Venta> ventas;
}
