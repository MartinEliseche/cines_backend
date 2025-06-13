package com.cines.cines.Controllers;

import com.cines.cines.DTOs.Request.PeliculaCineRequestDTO;
import com.cines.cines.DTOs.Response.PeliculaCineResponseDTO;
import com.cines.cines.DTOs.Update.PeliculaCineUpdateDTO;

import com.cines.cines.Services.PeliculaCineService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pelicines")
public class PeliculaCineController {

    private PeliculaCineService peliculaCineService;

    @GetMapping
    public ResponseEntity<List<PeliculaCineResponseDTO>> getAll() {
        return peliculaCineService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaCineResponseDTO> getById(@PathVariable Long id) {
        return peliculaCineService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> createPeliculaCine(@RequestBody PeliculaCineRequestDTO peliculaCineRequestDTO) {
        return peliculaCineService.createPeliculaCine(peliculaCineRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePeliculaCine(@PathVariable Long id) {
        return peliculaCineService.deletePeliculaCine(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCine(@PathVariable Long id, @RequestBody PeliculaCineUpdateDTO peliculaCineUpdateDTO) {
        return peliculaCineService.updatePeliculaCine(id, peliculaCineUpdateDTO);
    }
}
