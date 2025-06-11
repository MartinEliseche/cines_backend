package com.cines.cines.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CineResponseDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String ciudad; 
}
