package intranet.restaurante.Entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;

    private String user;  
    private String username;   
    private String contrasena;  

    @ManyToOne
    @JoinColumn(name = "Rol_idRol")
    @JsonManagedReference
    private Rol rol;
}