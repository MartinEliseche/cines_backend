package com.cines.cines.Entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;

@Entity

@Table(
    name = "pelicula_cine",
    uniqueConstraints = @UniqueConstraint(columnNames = {"pelicula_id", "cine_id"})
)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaCine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "cine_id", nullable = false)
    private Cine cine;
}
