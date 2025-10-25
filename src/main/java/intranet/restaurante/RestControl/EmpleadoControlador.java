package intranet.restaurante.RestControl;

import intranet.restaurante.DTO.LoginDTO;
import intranet.restaurante.Entidades.Empleado;
import intranet.restaurante.Entidades.Rol;
import intranet.restaurante.Servicios.EmpleadoService;
import intranet.restaurante.Servicios.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "http://localhost:9050")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private RolService rolService;

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/{id}")
    public Empleado obtenerEmpleado(@PathVariable Integer id) {
        return empleadoService.obtenerEmpleadoPorId(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        Rol rol = rolService.obtenerRolPorId(empleado.getRol().getIdRol());
        empleado.setRol(rol);
        return empleadoService.crearEmpleado(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        Rol rol = rolService.obtenerRolPorId(empleado.getRol().getIdRol());
        empleado.setRol(rol);
        empleado.setIdEmpleado(id);
        return empleadoService.actualizarEmpleado(empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable Integer id) {
        empleadoService.eliminarEmpleado(id);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Empleado empleadoAutenticado = empleadoService.autenticarEmpleado(
            loginDTO.getUsername(), loginDTO.getContrasena()
        );
        if (empleadoAutenticado == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Usuario o contraseña incorrectos.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        empleadoAutenticado.setContrasena(null);
        return ResponseEntity.ok(empleadoAutenticado);
    }
}
