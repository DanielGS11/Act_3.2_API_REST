package com.activity.tiendajuegos.controllers;

import com.activity.tiendajuegos.services.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.activity.tiendajuegos.models.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@Tag(name = "Ventas", description = "API de Gestion de las ventas de la Tienda")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping("/cliente/{id}")
    @Operation(summary = "Ver historial de compras de un Cliente", description = "Mira el historial de compras realizadas" +
            " por un cliente con un ID determinado")
    public ResponseEntity<List<Venta>> historial(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ventaService.verHistorialClientePorID(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
