package com.cines.cines.Controllers;

import com.cines.cines.DTOs.Request.CineRequestDTO;
import com.cines.cines.DTOs.Response.CineResponseDTO;
import com.cines.cines.DTOs.Update.CineUpdateDTO;

import com.cines.cines.Services.CineService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cines")
public class CineController {

    private CineService cineService;

    @GetMapping
    public ResponseEntity<List<CineResponseDTO>> getAll() {
        return cineService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CineResponseDTO> getById(@PathVariable Long id) {
        return cineService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> createCine(@RequestBody CineRequestDTO cineRequestDTO) {
        return cineService.createCine(cineRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCine(@PathVariable Long id) {
        return cineService.deleteCine(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCine(@PathVariable Long id, @RequestBody CineUpdateDTO cineUpdateDTO) {
        return cineService.updateCine(id, cineUpdateDTO);
    }

}
