package com.cines.cines.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaCineRequestDTO {

    @NotNull(message = "La Pelicula Na Puede Ser Nula")
    @Positive(message = "La Pelicula No Puede Ser Negativa")
    private Long pelicula_id;

    @NotNull(message = "El Cine No Puede Ser Nulo")
    @Positive(message = "El Cine No Puede Ser Negativo")
    private Long cine_id;
}
