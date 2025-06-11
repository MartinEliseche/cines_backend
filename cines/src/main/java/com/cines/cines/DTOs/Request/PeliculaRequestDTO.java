package com.cines.cines.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaRequestDTO {

    @NotNull(message = "El Tutulo No Puede Ser Nulo")
    private String titulo;

    @NotNull(message = "El Genero No Puede Ser Nulo")
    private String genero;

    @NotNull(message = "La Duracion No Puede Ser Nula")
    @Positive(message = "La Duracion No Puede Ser Negativa")
    private Integer duracionMin;

    @NotNull(message = "El Anio No Puede Ser Nulo")
    @Positive(message = "El Anio No Puede Ser Negativo")
    private Integer anio;
}
