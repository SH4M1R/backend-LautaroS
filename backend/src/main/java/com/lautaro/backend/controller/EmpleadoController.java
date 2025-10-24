package com.lautaro.backend.controller;

import com.lautaro.backend.model.Empleado;
import com.lautaro.backend.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "http://localhost:5173")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> listar() {
        return empleadoService.listarEmpleados();
    }

    @PostMapping
    public Empleado guardar(@RequestBody Empleado empleado) {
        return empleadoService.guardarEmpleado(empleado);
    }

    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        Empleado existente = empleadoService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        existente.setUsername(empleado.getUsername());
        existente.setPassword(empleado.getPassword());
        existente.setRol(empleado.getRol());
        existente.setNombre(empleado.getNombre());
        return empleadoService.guardarEmpleado(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
    }

    // Se eliminó el método /login de aquí, ya está en AuthController
}
