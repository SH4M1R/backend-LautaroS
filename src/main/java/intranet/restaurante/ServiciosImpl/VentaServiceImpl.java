package intranet.restaurante.ServiciosImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import intranet.restaurante.DAO.DetalleVentaDAO;
import intranet.restaurante.DAO.ProductoDAO;
import intranet.restaurante.DAO.VentaDAO;
import intranet.restaurante.DTO.VentaRequest;
import intranet.restaurante.Entidades.Cliente;
import intranet.restaurante.Entidades.DetalleVenta;
import intranet.restaurante.Entidades.Producto;
import intranet.restaurante.Entidades.Venta;
import intranet.restaurante.Servicios.ClienteService;

import java.time.LocalDateTime;

@Service
public class VentaServiceImpl {

    @Autowired
    private VentaDAO ventaDAO;

    @Autowired
    private DetalleVentaDAO detalleDAO;

    @Autowired
    private ProductoDAO productoDAO;

    @Autowired
    private ClienteService clienteService;

    @Transactional
    public Venta registrarVenta(VentaRequest request) {

        Cliente cliente = clienteService.obtenerOCrearCliente(request.getCliente());

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setTotal(request.getTotal());
        venta.setFechaVenta(LocalDateTime.now());

        venta = ventaDAO.save(venta);

        for (VentaRequest.DetalleProductoRequest det : request.getDetalles()) {
            Producto producto = productoDAO.findById(det.getProducto().getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + det.getProducto().getIdProducto()));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setSubtotal(det.getSubtotal());
            detalle.setMetodoPago(det.getMetodoPago());
            detalle.setMontoPagado(det.getMontoPagado());
            detalle.setVuelto(det.getVuelto());
            detalle.setCodigoIzipay(det.getCodigoIzipay());
            detalle.setNumeroTarjeta(det.getNumeroTarjeta());

            detalleDAO.save(detalle);
        }

        return venta;
    }
}
