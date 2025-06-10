package com.cines.cines.Entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "genero", length = 100, nullable = false)
    private String genero;
    
    @Column(name = "duracionMin", nullable = false)
    private Integer duracionMin;  

    @Column(name = "anio", nullable = false)
    private Integer anio;  
}
