package intranet.restaurante.Config;

import intranet.restaurante.DAO.EmpleadoDAO;
import intranet.restaurante.Entidades.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado emp = empleadoDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado"));

        return new User(emp.getUsername(), emp.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + emp.getRol())));
    }
}
