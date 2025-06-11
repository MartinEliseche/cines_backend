package com.cines.cines.DTOs.Update;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CineUpdateDTO {

    private String nombre;
    private String direccion;
    private String ciudad;
}
