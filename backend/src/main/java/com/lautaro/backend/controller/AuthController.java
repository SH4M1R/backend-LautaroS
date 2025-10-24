package com.lautaro.backend.controller;

import com.lautaro.backend.model.Empleado;
import com.lautaro.backend.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:9050")
public class AuthController {

    private final EmpleadoService empleadoService;

    public AuthController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Empleado> optionalEmpleado = empleadoService.obtenerPorUsername(loginRequest.getUsername());

        if (optionalEmpleado.isEmpty()) {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }

        Empleado empleado = optionalEmpleado.get();

        if (!empleado.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }

        // Devuelve solo la información necesaria
        LoginResponse response = new LoginResponse(empleado.getNombre(), empleado.getRol(), empleado.getUsername());
        return ResponseEntity.ok(response);
    }

    // Clase interna para el request
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // Clase interna para el response
    public static class LoginResponse {
        private String nombre;
        private String rol;
        private String username;

        public LoginResponse(String nombre, String rol, String username) {
            this.nombre = nombre;
            this.rol = rol;
            this.username = username;
        }

        public String getNombre() { return nombre; }
        public String getRol() { return rol; }
        public String getUsername() { return username; }
    }
}
