package intranet.restaurante.ServiciosImpl;

import intranet.restaurante.DAO.EmpleadoDAO;
import intranet.restaurante.Entidades.Empleado;
import intranet.restaurante.Servicios.AuthService;
import intranet.restaurante.Config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(String username, String password) {
        Empleado emp = empleadoDAO.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        if (!passwordEncoder.matches(password, emp.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return jwtTokenProvider.generateToken(emp.getUsername(), emp.getRol());
    }

    @Override
    public Map<String, String> register(Empleado empleado) {
        if (empleadoDAO.findByUsername(empleado.getUsername()).isPresent()) {
            return Map.of("error", "El empleado ya existe");
        }

        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        empleadoDAO.save(empleado);

        return Map.of("message", "Empleado registrado correctamente");
    }

    @Override
    public Map<String, String> logout() {
        // JWT es stateless, no hay que invalidarlo en el backend
        return Map.of("message", "Sesión cerrada correctamente");
    }
}
