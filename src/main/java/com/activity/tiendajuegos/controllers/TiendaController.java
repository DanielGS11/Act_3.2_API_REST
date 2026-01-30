package com.activity.tiendajuegos.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.activity.tiendajuegos.models.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.activity.tiendajuegos.request.RequestCompra;
import com.activity.tiendajuegos.services.TiendaService;

@RestController
@RequestMapping("/api/tienda")
@Tag(name = "Tienda", description = "API de Gestion de la Tienda")
public class TiendaController {
    @Autowired
    private TiendaService tiendaService;

    @PostMapping("/comprar")
    @Operation(summary = "Comprar un juego", description = "Mediante la id del cliente y el juego a comprar, " +
            "realiza la compra de dicho juego")
    public ResponseEntity<Venta> comprar(@RequestBody RequestCompra compra) {
        try {
            return new ResponseEntity<>(tiendaService.comprar(compra.getIdCliente(), compra.getIdVideojuego()), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("No existe")) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.badRequest().build();
        }
    }
}
