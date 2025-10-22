package intranet.restaurante.ServiciosImpl;

import intranet.restaurante.DAO.EmpleadoDAO;
import intranet.restaurante.Entidades.Empleado;
import intranet.restaurante.Servicios.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override   
    public Empleado crearEmpleado(Empleado empleado) {
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        return empleadoDAO.save(empleado);
    }

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoDAO.findAll();
    }

    @Override
    public Empleado obtenerEmpleadoPorId(Integer idEmpleado) {
        return empleadoDAO.findById(idEmpleado).orElseThrow();
    }

    @Override
    public Empleado actualizarEmpleado(Empleado empleado) {
        if (empleado.getPassword() != null) {
            empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        }
        return empleadoDAO.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Integer idEmpleado) {
        empleadoDAO.deleteById(idEmpleado);
    }
}
