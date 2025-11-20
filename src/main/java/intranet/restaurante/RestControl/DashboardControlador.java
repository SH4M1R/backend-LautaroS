package intranet.restaurante.RestControl;

import intranet.restaurante.DTO.DashboardDTO;
import intranet.restaurante.Servicios.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:9050")
public class DashboardControlador {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDTO> obtenerDashboard() {
        try {
            DashboardDTO dto = dashboardService.obtenerDashboard();
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
