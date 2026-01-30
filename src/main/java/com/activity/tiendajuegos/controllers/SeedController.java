package com.activity.tiendajuegos.controllers;

import com.activity.tiendajuegos.models.Cliente;
import com.activity.tiendajuegos.models.Genero;
import com.activity.tiendajuegos.models.Videojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.activity.tiendajuegos.repository.ClienteRepository;
import com.activity.tiendajuegos.repository.VentaRepository;
import com.activity.tiendajuegos.repository.VideojuegoRepository;

@RestController
@RequestMapping("/utils")
public class SeedController {
    @Autowired
    private VentaRepository saleRepository;

    @Autowired
    private ClienteRepository clientRepository;

    @Autowired
    private VideojuegoRepository gameRepository;

    @PostMapping("/seed")
    public void plantillaDatos() {

        // Borrar datos existentes
        saleRepository.deleteAll();
        clientRepository.deleteAll();
        gameRepository.deleteAll();

        // Crear clientes
        Cliente c1 = new Cliente(null, "Ana", "ana@email.com", 1000);
        Cliente c2 = new Cliente(null, "Luis", "luis@email.com", 5000);

        // Crear videojuegos
        Videojuego v1 = new Videojuego(null, "Zelda", Genero.AVENTURAS, 40, 5);
        Videojuego v2 = new Videojuego(null, "FIFA", Genero.DEPORTES, 60, 3);

        clientRepository.save(c1);
        clientRepository.save(c2);

        gameRepository.save(v1);
        gameRepository.save(v2);
    }
}
