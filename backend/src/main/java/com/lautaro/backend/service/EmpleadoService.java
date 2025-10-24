package com.lautaro.backend.service;

import com.lautaro.backend.model.Empleado;
import com.lautaro.backend.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    // Listar todos los empleados
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    // Guardar un empleado (nuevo o actualizado)
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Obtener empleado por ID
    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    // Eliminar empleado
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }

    // Nuevo método de login
    public Optional<Empleado> obtenerPorUsernameYPassword(String username, String password) {
        return empleadoRepository.findByUsername(username)
                .filter(e -> e.getPassword().equals(password));
    }

    // Buscar solo por username
    public Optional<Empleado> obtenerPorUsername(String username) {
        return empleadoRepository.findByUsername(username);
    }
}
