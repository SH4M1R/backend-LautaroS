package intranet.restaurante.Servicios;

import intranet.restaurante.Entidades.Empleado;
import java.util.List;

public interface EmpleadoService {
    List<Empleado> listarEmpleados();
    Empleado crearEmpleado(Empleado empleado);
    Empleado obtenerEmpleadoPorId(Integer idEmpleado);
    Empleado actualizarEmpleado(Empleado empleado);
    void eliminarEmpleado(Integer idEmpleado);
}
