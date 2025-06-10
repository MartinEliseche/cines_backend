package com.cines.cines.Entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;
    
    @Column(name = "ciudad", length = 100, nullable = false)
    private String ciudad;  
}
