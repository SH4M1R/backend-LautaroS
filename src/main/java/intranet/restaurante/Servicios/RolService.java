package intranet.restaurante.Servicios;

import intranet.restaurante.Entidades.Rol;
import java.util.List;

public interface RolService {
    List<Rol> listarRoles();
    Rol crearRol(Rol rol);
    Rol obtenerRolPorId(Integer idRol);
    Rol actualizarRol(Rol rol);
    void eliminarRol(Integer idRol);
}