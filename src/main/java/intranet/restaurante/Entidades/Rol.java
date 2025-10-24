package intranet.restaurante.Entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@Table(name = "Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    private String rol;
    @OneToMany(mappedBy = "rol")
     @JsonBackReference
    private List<Empleado> empleados;
}