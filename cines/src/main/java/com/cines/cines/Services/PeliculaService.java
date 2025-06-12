package com.cines.cines.Services;

import com.cines.cines.Entities.Pelicula;
import com.cines.cines.Repositories.PeliculaRepository;
import com.cines.cines.DTOs.Request.PeliculaRequestDTO;
import com.cines.cines.DTOs.Response.PeliculaResponseDTO;
import com.cines.cines.DTOs.Update.PeliculaUpdateDTO;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PeliculaService {

    private PeliculaRepository peliculaRepository;

    public ResponseEntity<List<PeliculaResponseDTO>> getAll() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                peliculas.stream()
                        .map(pelicula -> new PeliculaResponseDTO(
                                pelicula.getId(),
                                pelicula.getTitulo(),
                                pelicula.getGenero(),
                                pelicula.getDuracionMin(),
                                pelicula.getAnio()))
                        .toList());
    }

    public Optional<Pelicula> getEntityById(Long id) {
        return peliculaRepository.findById(id);
    }

    public ResponseEntity<PeliculaResponseDTO> getById(Long id) {
        Optional<Pelicula> peliculaOptional = getEntityById(id);

        if (peliculaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new PeliculaResponseDTO(
                            peliculaOptional.get().getId(),
                            peliculaOptional.get().getTitulo(),
                            peliculaOptional.get().getGenero(),
                            peliculaOptional.get().getDuracionMin(),
                            peliculaOptional.get().getAnio()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<?> createPelicula(PeliculaRequestDTO peliculaRequestDTO) {

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(peliculaRequestDTO.getTitulo().toUpperCase());
        pelicula.setGenero(peliculaRequestDTO.getGenero().toUpperCase());
        pelicula.setDuracionMin(peliculaRequestDTO.getDuracionMin());
        pelicula.setAnio(peliculaRequestDTO.getAnio());
        peliculaRepository.save(pelicula);
        return ResponseEntity.status(HttpStatus.OK).body("Pelicula Creada");
    }

    public ResponseEntity<String> deletePelicula(Long id) {
        Optional<Pelicula> peliculaOptional = getEntityById(id);

        if (peliculaOptional.isPresent()) {
            peliculaRepository.delete(peliculaOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Pelicula Eliminada");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula No Encontrada");
    }

    public ResponseEntity<String> updatePelicula(Long id, PeliculaUpdateDTO peliculaUpdateDTO) {
    Optional<Pelicula> peliculaOptional = getEntityById(id);

    if (peliculaOptional.isPresent()) {
        Pelicula pelicula = peliculaOptional.get();

        if (peliculaUpdateDTO.getTitulo() != null) {
            pelicula.setTitulo(peliculaUpdateDTO.getTitulo().toUpperCase());
        }
        if (peliculaUpdateDTO.getGenero() != null) {
            pelicula.setGenero(peliculaUpdateDTO.getGenero().toUpperCase());
        }
        if (peliculaUpdateDTO.getDuracionMin() != null) {
            pelicula.setDuracionMin(peliculaUpdateDTO.getDuracionMin());
        }
        if (peliculaUpdateDTO.getAnio() != null) {
            pelicula.setAnio(peliculaUpdateDTO.getAnio());
        }

        peliculaRepository.save(pelicula);
        return ResponseEntity.status(HttpStatus.OK).body("Pelicula Actualizada");
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula No Encontrada");
    }

}
