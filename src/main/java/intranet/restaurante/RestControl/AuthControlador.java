package intranet.restaurante.RestControl;

import intranet.restaurante.Config.JwtTokenProvider;
import intranet.restaurante.Entidades.Empleado;
import intranet.restaurante.Servicios.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthControlador {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String token = authService.login(credentials.get("username"), credentials.get("password"));
        // Obtener rol desde el token
        String role = jwtTokenProvider.getRole(token);
        return ResponseEntity.ok(Map.of(
            "token", token,
            "role", role
        ));
    }


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(authService.register(empleado));
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        return ResponseEntity.ok(authService.logout());
    }
}
