package cl.duoc.eft.usuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El campo no puede estar vacio")
    private String nombre;
    @NotBlank(message = "El campo no puede estar vacio")
    private String email;
    @NotBlank(message = "El campo no puede estar vacio")
    private String password;
    @NotBlank(message = "El campo no puede estar vacio")
    private String rol;

}
