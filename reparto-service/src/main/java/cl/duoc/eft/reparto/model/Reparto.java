package cl.duoc.eft.reparto.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "repartos")
public class Reparto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private Long repartidorId;
    private String estado;

}
