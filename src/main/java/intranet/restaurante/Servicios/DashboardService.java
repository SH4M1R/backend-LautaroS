package intranet.restaurante.Servicios;

import intranet.restaurante.DAO.VentaDAO;
import intranet.restaurante.DAO.DetalleVentaDAO;
import intranet.restaurante.DAO.ProductoDAO;
import intranet.restaurante.DAO.ClienteDAO;
import intranet.restaurante.DTO.DashboardDTO;
import intranet.restaurante.DTO.PlatoVendidoDTO;
import intranet.restaurante.Entidades.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;

@Service
public class DashboardService {

    @Autowired
    private VentaDAO ventaDAO; // Para todas las ventas

    @Autowired
    private DetalleVentaDAO detalleVentaDAO; // Para platos más vendidos

    @Autowired
    private ProductoDAO productoDAO;

    @Autowired
    private ClienteDAO clienteDAO;

    public DashboardDTO obtenerDashboard() {
        DashboardDTO dto = new DashboardDTO();

        LocalDate hoy = LocalDate.now();
        List<Venta> todasVentas = ventaDAO.findAll();

        // Ventas de hoy
        dto.setVentasHoy(
            todasVentas.stream()
                       .filter(v -> v.getFechaVenta().toLocalDate().equals(hoy))
                       .map(Venta::getTotal)
                       .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        // Métricas generales
        dto.setVentasRealizadas((long) todasVentas.size());
        dto.setPlatosExistentes(productoDAO.count());
        dto.setClientes(clienteDAO.count());

        // Ventas semanales
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

        // Platos más vendidos
        List<PlatoVendidoDTO> platosDTO = detalleVentaDAO.findPlatosMasVendidos();
        List<DashboardDTO.PlatoVendido> platosMasVendidos = new ArrayList<>();
        for (PlatoVendidoDTO p : platosDTO) {
            DashboardDTO.PlatoVendido dp = new DashboardDTO.PlatoVendido();
            dp.setNombre(p.getNombre());
            dp.setCantidad(p.getCantidad());
            platosMasVendidos.add(dp);
        }
        dto.setPlatosMasVendidos(platosMasVendidos);

        return dto;
    }
}
