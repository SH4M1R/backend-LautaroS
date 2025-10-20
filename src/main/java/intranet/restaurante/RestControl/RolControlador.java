package intranet.restaurante.RestControl;

import intranet.restaurante.Entidades.Rol;
import intranet.restaurante.Servicios.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:9050")
public class RolControlador {

    @Autowired
    private RolService rolService;

    @GetMapping
    public List<Rol> listarRoles() {return rolService.listarRoles();}

    @GetMapping("/{id}")
    public Rol obtenerRol(@PathVariable Integer id) {return rolService.obtenerRolPorId(id);}

    @PostMapping
    public Rol crearRol(@RequestBody Rol rol) {return rolService.crearRol(rol);}

    @PutMapping("/{id}")
    public Rol actualizarRol(@PathVariable Integer id, @RequestBody Rol rol) {rol.setIdRol(id);
        return rolService.actualizarRol(rol);}

    @DeleteMapping("/{id}")
    public void eliminarRol(@PathVariable Integer id) {rolService.eliminarRol(id);}
}