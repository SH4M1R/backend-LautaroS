package com.lautaro.backend.controller;

import com.lautaro.backend.model.Producto;
import com.lautaro.backend.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:9050") // <-- tu frontend
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    private final String uploadDir = "src/main/resources/static/images/";

    // Listar todos los productos
    @GetMapping
    public Iterable<Producto> getAll() {
        return productoRepository.findAll();
    }

    // Crear producto
    @PostMapping
    public ResponseEntity<Producto> createProducto(
            @RequestPart("producto") String productoJson,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {
        Producto producto = new com.fasterxml.jackson.databind.ObjectMapper()
                .readValue(productoJson, Producto.class);

        if (imagen != null && !imagen.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            imagen.transferTo(path.toFile());
            producto.setImagen("/images/" + fileName);
        }

        productoRepository.save(producto);
        return ResponseEntity.ok(producto);
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(
            @PathVariable Long id,
            @RequestPart("producto") String productoJson,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) throws IOException {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Producto producto = new com.fasterxml.jackson.databind.ObjectMapper()
                .readValue(productoJson, Producto.class);

        productoExistente.setProducto(producto.getProducto());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecioVenta(producto.getPrecioVenta());
        productoExistente.setEstado(producto.getEstado());
        productoExistente.setCategoria(producto.getCategoria());

        if (imagen != null && !imagen.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            imagen.transferTo(path.toFile());
            productoExistente.setImagen("/images/" + fileName);
        }

        productoRepository.save(productoExistente);
        return ResponseEntity.ok(productoExistente);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
