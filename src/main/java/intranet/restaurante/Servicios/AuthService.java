package intranet.restaurante.Servicios;

import intranet.restaurante.Entidades.Empleado;
import java.util.Map;

public interface AuthService {
    String login(String username, String password);
    Map<String, String> register(Empleado empleado);
    Map<String, String> logout();
}
