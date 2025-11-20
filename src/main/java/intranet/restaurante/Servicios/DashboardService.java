package intranet.restaurante.Servicios;

import intranet.restaurante.DAO.*;
import intranet.restaurante.DTO.DashboardDTO;
import intranet.restaurante.Entidades.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.*;
import java.math.BigDecimal;

@Service
public class DashboardService {

    @Autowired
    private VentaDAO ventaDAO;

    @Autowired
    private ProductoDAO productoDAO;

    @Autowired
    private ClienteDAO clienteDAO;

    public DashboardDTO obtenerDashboard() {
        DashboardDTO dto = new DashboardDTO();

        LocalDate hoy = LocalDate.now();
        List<Venta> todasVentas = ventaDAO.findAll();

        dto.setVentasHoy(
            todasVentas.stream()
                       .filter(v -> v.getFechaVenta().toLocalDate().equals(hoy))
                       .map(Venta::getTotal)
                       .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
        dto.setVentasRealizadas((long) todasVentas.size());
        dto.setPlatosExistentes(productoDAO.count());
        dto.setClientes(clienteDAO.count());
        List<DashboardDTO.VentasDia> semanal = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            LocalDate dia = hoy.minusDays(i);
            BigDecimal totalDia = todasVentas.stream()
                .filter(v -> v.getFechaVenta().toLocalDate().equals(dia))
                .map(Venta::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            DashboardDTO.VentasDia vd = new DashboardDTO.VentasDia();
            vd.setDia(dia.getDayOfWeek().toString());
            vd.setTotal(totalDia);
            semanal.add(vd);
        }
        dto.setVentasSemanal(semanal);

        return dto;
    }
}