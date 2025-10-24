package intranet.restaurante.Data;

import intranet.restaurante.Entidades.Empleado;
import intranet.restaurante.Entidades.Rol;
import intranet.restaurante.DAO.EmpleadoDAO;
import intranet.restaurante.DAO.RolDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class SimpleDataLoader implements CommandLineRunner {

    @Autowired
    private RolDAO rolDAO;

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Override
    public void run(String... args) throws Exception {
        if (rolDAO.count() == 0) {
            Rol rolAdmin = new Rol();
            rolAdmin.setRol("ADMINISTRADOR");

            Rol rolVendedor = new Rol();
            rolVendedor.setRol("VENDEDOR");

            Rol rolMesero = new Rol();
            rolMesero.setRol("MESERO");

            rolDAO.save(rolAdmin);
            rolDAO.save(rolVendedor);
            rolDAO.save(rolMesero);

            Empleado admin = new Empleado();
            admin.setUser("Juan Pérez");
            admin.setUsername("admin");
            admin.setContrasena("admin123");
            admin.setRol(rolAdmin);

            Empleado vendedor = new Empleado();
            vendedor.setUser("María López");
            vendedor.setUsername("vendedor");
            vendedor.setContrasena("venta123");
            vendedor.setRol(rolVendedor);

            Empleado mesero = new Empleado();
            mesero.setUser("Carlos Ramos");
            mesero.setUsername("mesero");
            mesero.setContrasena("mesa123");
            mesero.setRol(rolMesero);

            empleadoDAO.save(admin);
            empleadoDAO.save(vendedor);
            empleadoDAO.save(mesero);
        } else {
            System.out.println("Los datos iniciales ya existen, no se cargaron nuevamente.");
        }
    }
}
