package com.lautaro.backend.repository;

import com.lautaro.backend.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByUsername(String username);
}
