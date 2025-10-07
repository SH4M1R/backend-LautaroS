package intranet.restaurante.ServiciosImpl;

import intranet.restaurante.DAO.EmpleadoDAO;
import intranet.restaurante.Entidades.Empleado;
import intranet.restaurante.Servicios.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired 
    private EmpleadoDAO empleadoDAO;

    @Override
    public Empleado crearEmpleado(Empleado Empleado) {return empleadoDAO.save(Empleado);}

    @Override
    public List<Empleado> listarEmpleados() {return empleadoDAO.findAll();}

    @Override
    public Empleado obtenerEmpleadoPorId(Integer idEmpleado) {return empleadoDAO.findById(idEmpleado).get();}

    @Override
    public Empleado actualizarEmpleado(Empleado Empleado) {return empleadoDAO.save(Empleado);}

    @Override
    public void eliminarEmpleado(Integer idEmpleado) {empleadoDAO.deleteById(idEmpleado);}
}