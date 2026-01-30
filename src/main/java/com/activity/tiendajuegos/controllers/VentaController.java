package com.activity.tiendajuegos.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import com.activity.tiendajuegos.models.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.activity.tiendajuegos.repository.VentaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@Tag(name = "Ventas", description = "API de Gestion de las ventas de la Tienda")
public class VentaController {
    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping("/cliente/{id}")
    public List<Venta> historial(@PathVariable Long id) {
        return ventaRepository.findByClienteId(id);
    }
}
