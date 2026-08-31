package cl.duoc.eft.producto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El campo no puede estar vacio")
    private String codigo;
    @NotBlank(message = "El campo no puede estar vacio")
    private String nombre;
    @NotBlank(message = "El campo no puede estar vacio")
    private String descripcion;
    @NotNull
    private Double precio;

}
