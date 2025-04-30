package com.example.repasopc1.controllers;

import com.example.repasopc1.entities.Producto;
import com.example.repasopc1.repositories.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/disponibles")
    public List<Producto> productosDisponibles() {
        return productoRepository.findByStockGreaterThan(0);
    }

    @GetMapping("/{id}")
    public Producto detalleProducto(@PathVariable Long id) {
        return productoRepository.findById(id).orElseThrow();
    }

    @PutMapping("/reponer/{id}")
    public Producto reponerStock(@PathVariable Long id, @RequestParam int cantidad) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        producto.setStock(producto.getStock() + cantidad);
        return productoRepository.save(producto);
    }
}
