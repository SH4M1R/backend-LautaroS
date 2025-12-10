package intranet.restaurante.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class VentaRequest {
     private BigDecimal total;
    private BigDecimal montoPagado;
    private BigDecimal vuelto;
    private String metodoPago;
    private CajaRequest caja;
    private String codigoIzipay;
    private String numeroTarjeta;
    private ClienteRequest cliente;
    private List<DetalleProductoRequest> detalles;

    @Data
    public static class CajaRequest {
        private Integer idCaja;
    }

    @Data
    public static class DetalleProductoRequest {
        private ProductoId producto;
        private Integer stock;
        private BigDecimal subtotal;
        private String metodoPago;
        private BigDecimal montoPagado;
        private BigDecimal vuelto;
        private String codigoIzipay;
        private Long cantidad;
        private String numeroTarjeta;
    }

    @Data
    public static class ProductoId {
        private Integer idProducto;
    }
}
