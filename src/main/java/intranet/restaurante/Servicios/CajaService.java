package intranet.restaurante.Servicios;

import intranet.restaurante.DAO.CajaDAO;
import intranet.restaurante.Entidades.Caja;
import intranet.restaurante.Entidades.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CajaService {

    @Autowired
    private CajaDAO cajaDAO;

    public Caja abrirCaja(BigDecimal montoInicial) {
        Caja caja = new Caja();
        caja.setMontoInicial(montoInicial);
        caja.setFechaApertura(LocalDateTime.now());
        caja.setTotalVentas(BigDecimal.ZERO);
        return cajaDAO.save(caja);
    }

    public BigDecimal cerrarCaja(Integer idCaja) {
    Caja caja = cajaDAO.findById(idCaja)
            .orElseThrow(() -> new RuntimeException("Caja no encontrada"));

    BigDecimal totalVentas = caja.getVentas() != null
            ? caja.getVentas().stream()
                .map(Venta::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
            : BigDecimal.ZERO;

    caja.setTotalVentas(totalVentas);
    caja.setFechaCierre(LocalDateTime.now());
    cajaDAO.save(caja);

    // Devuelve total en caja: monto inicial + totalVentas
    return caja.getMontoInicial().add(totalVentas);
}


    public Caja obtenerCajaHoy() {
        try {
            LocalDate hoy = LocalDate.now();
            // buscar la caja abierta hoy
            Caja caja = cajaDAO.findByFechaAperturaBetween(
                    hoy.atStartOfDay(),
                    hoy.plusDays(1).atStartOfDay()
            ).orElse(null);

            // Asegurarse que la lista de ventas no sea null
            if (caja != null && caja.getVentas() == null) {
                caja.setVentas(List.of());
            }

            return caja;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // nunca lanzar 500
        }
    }
}
