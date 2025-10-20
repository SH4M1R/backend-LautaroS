package intranet.restaurante.ServiciosImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import intranet.restaurante.DAO.RolDAO;
import intranet.restaurante.Entidades.Rol;
import intranet.restaurante.Servicios.RolService;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolDAO rolDAO;

    @Override
    public List<Rol> listarRoles() {return rolDAO.findAll();}

    @Override
    public Rol obtenerRolPorId(Integer idRol) {return rolDAO.findById(idRol).get();}

    @Override
    public Rol crearRol(Rol rol) {return rolDAO.save(rol);}

    @Override
    public Rol actualizarRol(Rol rol) {return rolDAO.save(rol);}

    @Override
    public void eliminarRol(Integer idRol) { rolDAO.deleteById(idRol);}
}