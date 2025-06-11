package com.cines.cines.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaResponseDTO {

    private Long id;
    private String titulo;
    private String genero;
    private Integer duracionMin;  
    private Integer anio;
}
