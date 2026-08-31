package cl.duoc.eft.reparto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "repartos")
public class Reparto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private Long repartidorId;
    @NotBlank(message = "El campo no puede estar vacio")
    private String estado;

}
