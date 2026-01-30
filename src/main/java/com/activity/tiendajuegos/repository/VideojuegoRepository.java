package com.activity.tiendajuegos.repository;

import com.activity.tiendajuegos.models.Genero;
import com.activity.tiendajuegos.models.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
    List<Videojuego> findByGenero(Genero genero);

    List<Videojuego> findByPrecioLessThan(double maxPrice);
}
