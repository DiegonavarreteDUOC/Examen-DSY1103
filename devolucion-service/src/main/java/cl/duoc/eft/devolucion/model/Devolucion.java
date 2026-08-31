package cl.duoc.eft.devolucion.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "devolucions")
public class Devolucion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private String motivo;
    private String estado;

}
