package com.cines.cines.DTOs.Update;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaCineUpdateDTO {

    @Positive(message = "La Pelicula No Puede Ser Negativa")
    private Long pelicula_id;

    @Positive(message = "El Cine No Puede Ser Negativo")
    private Long cine_id;
}
