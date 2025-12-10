package intranet.restaurante.ServiciosImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import intranet.restaurante.DAO.CajaDAO;
import intranet.restaurante.DAO.DetalleVentaDAO;
import intranet.restaurante.DAO.ProductoDAO;
import intranet.restaurante.DAO.VentaDAO;
import intranet.restaurante.DTO.VentaRequest;
import intranet.restaurante.Entidades.Caja;
import intranet.restaurante.Entidades.Cliente;
import intranet.restaurante.Entidades.DetalleVenta;
import intranet.restaurante.Entidades.Producto;
import intranet.restaurante.Entidades.Venta;
import intranet.restaurante.Servicios.ClienteService;
import intranet.restaurante.Servicios.VentaService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaDAO ventaDAO;

    @Autowired
    private DetalleVentaDAO detalleDAO;

    @Autowired
    private ProductoDAO productoDAO;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CajaDAO cajaDAO;

    @Transactional
    @Override
    public Venta registrarVenta(VentaRequest request) {

        Cliente cliente = clienteService.obtenerOCrearCliente(request.getCliente());

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setTotal(request.getTotal());
        venta.setFechaVenta(LocalDateTime.now());

        // Asignar la caja desde el request
        if (request.getCaja() != null && request.getCaja().getIdCaja() != null) {
            Caja caja = cajaDAO.findById(request.getCaja().getIdCaja())
                    .orElseThrow(() -> new RuntimeException("Caja no encontrada: " + request.getCaja().getIdCaja()));
            venta.setCaja(caja);
        } else {
            throw new RuntimeException("No se especificó la caja para la venta");
        }

        venta = ventaDAO.save(venta);

        if (request.getDetalles() != null) {
            for (VentaRequest.DetalleProductoRequest det : request.getDetalles()) {
                Producto producto = productoDAO.findById(det.getProducto().getIdProducto())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + det.getProducto().getIdProducto()));

                DetalleVenta detalle = new DetalleVenta();
                detalle.setVenta(venta);
                detalle.setProducto(producto);
                detalle.setCantidad(det.getCantidad());
                detalle.setSubtotal(det.getSubtotal());
                detalle.setMetodoPago(det.getMetodoPago());
                detalle.setMontoPagado(det.getMontoPagado());
                detalle.setVuelto(det.getVuelto());
                detalle.setCodigoIzipay(det.getCodigoIzipay());
                detalle.setNumeroTarjeta(det.getNumeroTarjeta());

                detalleDAO.save(detalle);
            }
        }

        return venta;
    }

    public Venta obtenerPorId(Integer id) {
    return ventaDAO.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada: " + id));
}


    @Override
    public List<Venta> listarTodas() {
        return ventaDAO.findAll();
    }
}
