package intranet.restaurante.ServiciosImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import intranet.restaurante.DAO.DetalleVentaDAO;
import intranet.restaurante.Entidades.DetalleVenta;
import intranet.restaurante.Servicios.DetalleVentaService;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaDAO detalleVentaDAO;

    @Override
    public DetalleVenta guardar(DetalleVenta detalle) {
        return detalleVentaDAO.save(detalle);
    }
}