package com.activity.tiendajuegos.services;

import com.activity.tiendajuegos.models.Venta;
import com.activity.tiendajuegos.repository.ClienteRepository;
import com.activity.tiendajuegos.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Venta> verHistorialClientePorID(Long id) {
        clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe el cliente con el id: " + id));

        return ventaRepository.findByClienteId(id);
    }
}
