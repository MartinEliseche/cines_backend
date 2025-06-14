package com.cines.cines.Controllers;

import com.cines.cines.DTOs.Request.PeliculaRequestDTO;
import com.cines.cines.DTOs.Response.PeliculaResponseDTO;
import com.cines.cines.DTOs.Update.PeliculaUpdateDTO;

import com.cines.cines.Services.PeliculaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<PeliculaResponseDTO>> getAll() {
        return peliculaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaResponseDTO> getById(@PathVariable Long id) {
        return peliculaService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> createPelicula(@Valid @RequestBody PeliculaRequestDTO peliculaRequestDTO) {
        return peliculaService.createPelicula(peliculaRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePelicula(@PathVariable Long id) {
        return peliculaService.deletePelicula(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updatePelicula(@PathVariable Long id, @RequestBody PeliculaUpdateDTO peliculaUpdateDTO) {
        return peliculaService.updatePelicula(id, peliculaUpdateDTO);
    }
}
