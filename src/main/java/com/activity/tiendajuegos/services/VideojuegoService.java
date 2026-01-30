package com.activity.tiendajuegos.services;

import jakarta.transaction.Transactional;
import com.activity.tiendajuegos.models.Genero;
import com.activity.tiendajuegos.models.Videojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.activity.tiendajuegos.repository.VentaRepository;
import com.activity.tiendajuegos.repository.VideojuegoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VideojuegoService {
    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public List<Videojuego> listarJuegos() {
        return videojuegoRepository.findAll();
    }

    public Optional<Videojuego> verJuegoPorID(Long id) {
        return videojuegoRepository.findById(id);
    }

    public List<Videojuego> listarJuegosPorGenero(Genero genero) {
        return videojuegoRepository.findByGenero(genero);
    }

    public List<Videojuego> listarJuegosConMenorPrecioQue(double maxPrice) {
        return videojuegoRepository.findByPrecioLessThan(maxPrice);
    }

    @Transactional
    public void borrar(Long id) {
        Videojuego videojuego = videojuegoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un juego con la id " + id));

        if (ventaRepository.findAll().stream()
                .anyMatch(venta -> venta.getVideojuego().equals(videojuego))) {
            throw new IllegalArgumentException("ese juego ya fue vendido");
        }

        videojuegoRepository.deleteById(id);
    }

    @Transactional
    public Videojuego guardar(Videojuego videojuego) {
        if (videojuego.getPrecio() < 0) {
            throw new IllegalArgumentException("El juego debe tener un precio mayor o igual a 0");
        }

        if (videojuego.getStock() < 0) {
            throw new IllegalArgumentException("El juego debe tener un stock mayor o igual a 0");
        }

        return videojuegoRepository.save(videojuego);
    }

    @Transactional
    public Videojuego actualizar(Long id, double newPrice, int newStock) {
        Videojuego videojuego = videojuegoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un juego con la id " + id));

        if (newPrice < 1) {
            throw new IllegalArgumentException("El precio nuevo del juego debe ser mayor a 0");
        }

        if (newStock < 0) {
            throw new IllegalArgumentException("El stock debe ser mayor o igual a 0");
        }

        videojuego.setStock(newStock);
        videojuego.setPrecio(newPrice);

        return videojuegoRepository.save(videojuego);
    }
}
