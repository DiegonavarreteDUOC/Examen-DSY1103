package cl.duoc.eft.carrito.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "carritos")
public class Carrito {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuarioId;

}
