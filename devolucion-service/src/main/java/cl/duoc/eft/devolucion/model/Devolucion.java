package cl.duoc.eft.devolucion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "devolucions")
public class Devolucion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    @NotBlank(message = "El campo no puede estar vacio")
    private String motivo;
    @NotBlank(message = "El campo no puede estar vacio")
    private String estado;

}
