package intranet.restaurante.Entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name = "Venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenta")
    private Integer idVenta;

    private BigDecimal total;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    @ManyToOne
    @JoinColumn(name = "Empleado_id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "Cliente_id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "caja_id") // Relación con Caja
    private Caja caja;

    @Lob
    private byte[] boleta;

    @OneToMany(mappedBy = "venta")
    @JsonIgnoreProperties("venta")
    private List<DetalleVenta> detalles;
}
