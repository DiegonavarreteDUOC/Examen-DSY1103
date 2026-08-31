package cl.duoc.eft.promocion.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "promocions")
public class Promocion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private Double porcentaje;
    private Boolean activa;

}
