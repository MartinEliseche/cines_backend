package com.cines.cines.Services;

import com.cines.cines.Entities.Pelicula;
import com.cines.cines.Entities.Cine;
import com.cines.cines.Entities.PeliculaCine;
import com.cines.cines.Repositories.PeliculaCineRepository;
import com.cines.cines.DTOs.Request.PeliculaCineRequestDTO;
import com.cines.cines.DTOs.Response.PeliculaCineResponseDTO;
import com.cines.cines.DTOs.Update.PeliculaCineUpdateDTO;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PeliculaCineService {

    private PeliculaCineRepository peliculaCineRepository;
    private PeliculaService peliculaService;
    private CineService cineService;

    public ResponseEntity<List<PeliculaCineResponseDTO>> getAll() {
        List<PeliculaCine> peliculasCines = peliculaCineRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                peliculasCines.stream()
                        .map(peliculaCine -> new PeliculaCineResponseDTO(
                                peliculaCine.getId(),
                                peliculaCine.getPelicula().getTitulo(),
                                peliculaCine.getCine().getNombre()))
                        .toList());
    }


    public Optional<PeliculaCine> getEntityById(Long id) {
        return peliculaCineRepository.findById(id);
    }
    

    public ResponseEntity<PeliculaCineResponseDTO> getById(Long id) {
        Optional<PeliculaCine> peliculaCineOptional = getEntityById(id);

        if (peliculaCineOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new PeliculaCineResponseDTO(
                            peliculaCineOptional.get().getId(),
                            peliculaCineOptional.get().getPelicula().getTitulo(),
                            peliculaCineOptional.get().getCine().getNombre()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    public ResponseEntity<?> createPeliculaCine(PeliculaCineRequestDTO peliculaCineRequestDTO) {

        PeliculaCine peliculaCine = new PeliculaCine();

        Optional<Pelicula> peliculaOptional = peliculaService.getEntityById(peliculaCineRequestDTO.getPelicula_id());
        Optional<Cine> cineOptional = cineService.getEntityById(peliculaCineRequestDTO.getCine_id());

        if (!peliculaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula No Encontrada");
        }

        if (!cineOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cine No Encontrado");
        }

        Pelicula pelicula = peliculaOptional.get();
        Cine cine = cineOptional.get();

        boolean existe = peliculaCineRepository
            .findByCine(cine)
            .stream()
            .anyMatch(x -> x.getPelicula().getId().equals(pelicula.getId()));

        if (existe)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Pelicula X Cine Ya Existente");

        peliculaCine.setPelicula(pelicula);
        peliculaCine.setCine(cine);
        peliculaCineRepository.save(peliculaCine);

        return ResponseEntity.status(HttpStatus.OK).body("Pelicula X Cine Creado");
    }


    public ResponseEntity<String> deletePeliculaCine(Long id) {
        Optional<PeliculaCine> peliculaCineOptional = getEntityById(id);

        if (peliculaCineOptional.isPresent()) {
            peliculaCineRepository.delete(peliculaCineOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Pelicula X Cine Eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula X Cine No Encontrado");
    }


    public ResponseEntity<String> updatePeliculaCine(Long id, PeliculaCineUpdateDTO peliculaCineUpdateDTO) {
        Optional<PeliculaCine> peliculaCineOptional = getEntityById(id);

        if (peliculaCineOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula X Cine No Encontrado");

        PeliculaCine peliculaCine = peliculaCineOptional.get();

        if (peliculaCineUpdateDTO.getPelicula_id() != null) {
            var optPel = peliculaService.getEntityById(peliculaCineUpdateDTO.getPelicula_id());
            if (optPel.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula No Encontrada");

            boolean duplicada = peliculaCineRepository
                .findByCine(peliculaCine.getCine()).stream()
                .anyMatch(x -> x.getPelicula().getId().equals(peliculaCineUpdateDTO.getPelicula_id()) && !x.getId().equals(peliculaCine.getId()));

            if (duplicada)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Pelicula X Cine Ya Existente");

            peliculaCine.setPelicula(optPel.get());
        }

        if (peliculaCineUpdateDTO.getCine_id() != null) {
            var optCine = cineService.getEntityById(peliculaCineUpdateDTO.getCine_id());
            if (optCine.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cine No Encontrado");

            boolean duplicada = peliculaCineRepository
                .findByPelicula(peliculaCine.getPelicula()).stream()
                .anyMatch(x -> x.getCine().getId().equals(peliculaCineUpdateDTO.getCine_id()) && !x.getId().equals(peliculaCine.getId()));

            if (duplicada)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Pelicula X Cine Ya Existente");

            peliculaCine.setCine(optCine.get());
        }

        peliculaCineRepository.save(peliculaCine);
        return ResponseEntity.status(HttpStatus.OK).body("Pelicula X Cine Actualizado");
    }
}
