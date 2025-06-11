package com.cines.cines.DTOs.Update;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaUpdateDTO {

    private String titulo;
    private String genero;
    private Integer duracionMin;  
    private Integer anio;
}
