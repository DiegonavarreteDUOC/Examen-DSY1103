package cl.duoc.eft.promocion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "promocions")
public class Promocion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El campo no puede estar vacio")
    private String codigo;
    @NotNull
    private Double porcentaje;
    private Boolean activa;

}
