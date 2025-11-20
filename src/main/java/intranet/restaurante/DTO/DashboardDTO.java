package intranet.restaurante.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DashboardDTO {
    private BigDecimal ventasHoy;
    private Long ventasRealizadas;
    private Long platosExistentes;
    private Long clientes;
    private List<VentasDia> ventasSemanal;

    @Data
    public static class VentasDia {
        private String dia;
        private BigDecimal total;
    }
}