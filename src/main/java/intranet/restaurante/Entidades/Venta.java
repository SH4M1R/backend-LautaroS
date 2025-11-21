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
    private Integer idVenta;
    
    private BigDecimal total;
    private LocalDateTime fechaVenta;

    @ManyToOne
    @JoinColumn(name = "Empleado_idEmpleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "Cliente_idCliente")
    private Cliente cliente;

    @Lob
    private byte[] boleta;

    @OneToMany(mappedBy = "venta")
    @JsonIgnoreProperties("venta")
    private List<DetalleVenta> detalles;
}