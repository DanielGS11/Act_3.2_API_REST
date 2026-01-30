package com.activity.tiendajuegos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok
@NoArgsConstructor // Constructor vacío requerido por JPA
@AllArgsConstructor // Constructor con todos los campos
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    private double precio;
    private int stock;
}
