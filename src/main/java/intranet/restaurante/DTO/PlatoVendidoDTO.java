package intranet.restaurante.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlatoVendidoDTO {
    private String nombre;
    private Long cantidad;
}
