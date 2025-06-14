package com.cines.cines.Services;

import com.cines.cines.Entities.Cine;
import com.cines.cines.Repositories.CineRepository;
import com.cines.cines.DTOs.Request.CineRequestDTO;
import com.cines.cines.DTOs.Response.CineResponseDTO;
import com.cines.cines.DTOs.Update.CineUpdateDTO;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CineService {

    private CineRepository cineRepository;

    public ResponseEntity<List<CineResponseDTO>> getAll() {
        List<Cine> cines = cineRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                cines.stream()
                        .map(cine -> new CineResponseDTO(
                                cine.getId(),
                                cine.getNombre(),
                                cine.getDireccion(),
                                cine.getCiudad()))
                        .toList());
    }

    public Optional<Cine> getEntityById(Long id) {
        return cineRepository.findById(id);
    }

    public ResponseEntity<CineResponseDTO> getById(Long id) {
        Optional<Cine> cineOptional = getEntityById(id);

        if (cineOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new CineResponseDTO(
                            cineOptional.get().getId(),
                            cineOptional.get().getNombre(),
                            cineOptional.get().getDireccion(),
                            cineOptional.get().getCiudad()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<?> createCine(CineRequestDTO cineRequestDTO) {

        Cine cine = new Cine();
        cine.setNombre(cineRequestDTO.getNombre().toUpperCase());
        cine.setDireccion(cineRequestDTO.getDireccion().toUpperCase());
        cine.setCiudad(cineRequestDTO.getCiudad().toUpperCase());
        cineRepository.save(cine);
        return ResponseEntity.status(HttpStatus.OK).body("Cine Creado");
    }

    public ResponseEntity<String> deleteCine(Long id) {
        Optional<Cine> cineOptional = getEntityById(id);

        if (cineOptional.isPresent()) {
            try {
                cineRepository.delete(cineOptional.get());
                return ResponseEntity.status(HttpStatus.OK).body("Cine Eliminado");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("No se puede eliminar el cine porque tiene relaciones asociadas.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error interno al eliminar el cine.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cine No Encontrado");
    }

    public ResponseEntity<String> updateCine(Long id, CineUpdateDTO cineUpdateDTO) {
        Optional<Cine> cineOptional = getEntityById(id);

        if (cineOptional.isPresent()) {
            Cine cine = cineOptional.get();

            if (cineUpdateDTO.getNombre() != null) {
                cine.setNombre(cineUpdateDTO.getNombre().toUpperCase());
            }
            if (cineUpdateDTO.getDireccion() != null) {
                cine.setDireccion(cineUpdateDTO.getDireccion().toUpperCase());
            }
            if (cineUpdateDTO.getCiudad() != null) {
                cine.setCiudad(cineUpdateDTO.getCiudad().toUpperCase());
            }

            cineRepository.save(cine);
            return ResponseEntity.status(HttpStatus.OK).body("Cine Actualizado");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cine No Encontrado");
    }

}
