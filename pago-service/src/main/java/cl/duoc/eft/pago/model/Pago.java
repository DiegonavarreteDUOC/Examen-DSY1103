package cl.duoc.eft.pago.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pagos")
public class Pago {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private Double monto;
    private String estado;

}
