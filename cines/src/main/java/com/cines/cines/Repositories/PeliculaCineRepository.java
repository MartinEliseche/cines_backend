package com.cines.cines.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cines.cines.Entities.PeliculaCine;
import com.cines.cines.Entities.Cine;
import com.cines.cines.Entities.Pelicula;
import java.util.List;

public interface PeliculaCineRepository extends JpaRepository<PeliculaCine, Long> {

    List<PeliculaCine> findByCine(Cine cine);
    List<PeliculaCine> findByPelicula(Pelicula pelicula);
}
