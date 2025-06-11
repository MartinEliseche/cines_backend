package com.cines.cines.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CineRequestDTO {

    @NotNull(message = "El Nombre del Cine No Puede Ser Nulo")
    private String nombre;

    @NotNull(message = "La Direccion No Puede Ser Nula")
    private String direccion;

    @NotNull(message = "La Ciudad No Puede Ser Nula")
    private String ciudad;
}
