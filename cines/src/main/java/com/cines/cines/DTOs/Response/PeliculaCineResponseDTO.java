package com.cines.cines.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaCineResponseDTO {

    private Long id;
    private String pelicula;
    private String cine;
}
