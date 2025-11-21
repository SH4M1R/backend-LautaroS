package intranet.restaurante.RestControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import intranet.restaurante.DAO.VentaDAO;
import intranet.restaurante.DTO.VentaRequest;
import intranet.restaurante.Entidades.Venta;
import intranet.restaurante.Servicios.BoletaService;
import intranet.restaurante.ServiciosImpl.VentaServiceImpl;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "http://localhost:9050")
public class VentaControlador {

    @Autowired
    private VentaServiceImpl ventaService;

    @Autowired
    private VentaDAO ventaDAO;

    @Autowired
    private BoletaService boletaService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarVenta(@RequestBody VentaRequest request) {
        try {
            Venta ventaRegistrada = ventaService.registrarVenta(request);
            // Devuelve JSON, no PDF
            return ResponseEntity.ok(ventaRegistrada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al registrar la venta: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error inesperado: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/boleta")
public ResponseEntity<byte[]> descargarBoleta(@PathVariable Integer id) {
    try {
        Venta venta = ventaDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Generar PDF tipo ticket
        byte[] pdf = boletaService.generarBoletaTicketPDF(venta);

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=boleta_" + id + ".pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdf);
    } catch (Exception e) {
        e.printStackTrace(); // log para ver errores reales
        return ResponseEntity.internalServerError().build();
    }
}


    @GetMapping("/listar")
    public ResponseEntity<?> listarVentas() {
        try {
            List<Venta> ventas = ventaDAO.findAll();
            return ResponseEntity.ok(ventas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al listar ventas: " + e.getMessage());
        }
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable Integer id) {
        try {
            Optional<Venta> ventaOpt = ventaDAO.findById(id);
            if (ventaOpt.isPresent()) {
                Venta venta = ventaOpt.get();
                return ResponseEntity.ok(venta);
            } else {
                return ResponseEntity.status(404).body("Venta no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener la venta: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
public ResponseEntity<Venta> obtenerDetalle(@PathVariable Integer id) {
    Venta venta = ventaService.obtenerPorId(id);
    if (venta != null) {
        return ResponseEntity.ok(venta);
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
