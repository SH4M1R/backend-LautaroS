package intranet.restaurante.RestControl;

import intranet.restaurante.Entidades.Caja;
import intranet.restaurante.Servicios.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:9050")
@RestController
@RequestMapping("/api/caja")
public class CajaControlador {

    @Autowired
    private CajaService cajaService;

    @PostMapping("/abrir")
    public ResponseEntity<?> abrirCaja(@RequestBody Map<String, Object> payload) {
        try {
            BigDecimal montoInicial = new BigDecimal(payload.get("montoInicial").toString());
            Caja caja = cajaService.abrirCaja(montoInicial);
            return ResponseEntity.ok(caja);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al abrir caja");
        }
    }

    @PostMapping("/cerrar")
public ResponseEntity<?> cerrarCaja(@RequestBody Map<String, Object> payload) {
    try {
        Integer idCaja = Integer.parseInt(payload.get("idCaja").toString());
        BigDecimal totalEnCaja = cajaService.cerrarCaja(idCaja);
        return ResponseEntity.ok(Map.of("totalEnCaja", totalEnCaja));
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("Error al cerrar caja");
    }
}


    @GetMapping("/hoy")
    public ResponseEntity<?> obtenerCajaHoy() {
        try {
            Caja caja = cajaService.obtenerCajaHoy();
            return ResponseEntity.ok(caja); // devuelve null si no hay caja abierta
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }
}
