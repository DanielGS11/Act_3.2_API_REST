package com.activity.tiendajuegos.services;

import jakarta.transaction.Transactional;
import com.activity.tiendajuegos.models.Cliente;
import com.activity.tiendajuegos.models.Venta;
import com.activity.tiendajuegos.models.Videojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.activity.tiendajuegos.repository.ClienteRepository;
import com.activity.tiendajuegos.repository.VentaRepository;
import com.activity.tiendajuegos.repository.VideojuegoRepository;

import java.time.LocalDate;

@Service
public class TiendaService {
    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Venta comprar(Long idCliente, Long idVideojuego) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("No existe un cliente con la id " + idCliente));

        Videojuego videojuego = videojuegoRepository.findById(idVideojuego)
                .orElseThrow(() -> new IllegalArgumentException("No existe un videojuego con la id " + idVideojuego));

        if (videojuego.getStock() <= 0) {
            throw new IllegalArgumentException("No queda stock para ese juego");
        }

        if (cliente.getSaldo() < videojuego.getPrecio()) {
            throw new IllegalArgumentException("El Cliente no cuenta con saldo suficiente");
        }

        cliente.setSaldo(cliente.getSaldo() - videojuego.getPrecio());

        videojuego.setStock(videojuego.getStock() - 1);

        Venta venta = new Venta();
        venta.setFecha(LocalDate.now());
        venta.setCoste(videojuego.getPrecio());
        venta.setCliente(cliente);
        venta.setVideojuego(videojuego);

        videojuegoRepository.save(videojuego);
        clienteRepository.save(cliente);
        return ventaRepository.save(venta);
    }
}
