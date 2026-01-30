package com.activity.tiendajuegos.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.activity.tiendajuegos.models.Genero;
import com.activity.tiendajuegos.models.Videojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.activity.tiendajuegos.services.VideojuegoService;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
@Tag(name = "Videojuegos", description = "API de Gestion de Videojuegos")
public class VideojuegoController {
    @Autowired
    private VideojuegoService videojuegoService;

    @GetMapping
    @Operation(summary = "Listar todos los Juegos", description = "Devuelve el listado de todos los juegos")
    public ResponseEntity<List<Videojuego>> listar() {
        return ResponseEntity.ok(videojuegoService.listarJuegos());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar un juego por su ID", description = "Devuelve un juego cuya ID coincida con la introducida")
    public ResponseEntity<Videojuego> buscarPorID(@PathVariable Long id) {
        return videojuegoService.verJuegoPorID(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un juego por su ID", description = "Devuelve un juego actualizado cuya ID coincida con la introducida")
    public ResponseEntity<Videojuego> actualizar(@PathVariable Long id, @RequestParam double newPrecio, @RequestParam int newStock) {
        return ResponseEntity.ok(videojuegoService.actualizar(id, newPrecio, newStock));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar un juego por su ID", description = "Devuelve un juego cuya ID coincida con la introducida")
    public ResponseEntity<Videojuego> borrar(@PathVariable Long id) {
        try {
            videojuegoService.borrar(id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("No existe")) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/genero/{genero}")
    @Operation(summary = "Listar los juegos de un genero", description = "Devuelve una lista de juegos cuyo genero" +
            " coincida con el introducido")
    public ResponseEntity<List<Videojuego>> buscarPorGenero(@PathVariable Genero genero) {
        return ResponseEntity.ok(videojuegoService.listarJuegosPorGenero(genero));
    }

    @GetMapping("/oferta")
    @Operation(summary = "Listar los juegos con un rango de precio", description = "Devuelve una lista de " +
            "juegos cuyo precio sea menor que el introducido")
    public ResponseEntity<List<Videojuego>> buscarPorPrecio(@RequestParam double maxPrecio) {
        return ResponseEntity.ok(videojuegoService.listarJuegosConMenorPrecioQue(maxPrecio));
    }
}
