package com.controller.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.producto.test.Producto;
import com.repository.test.ProductRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;


@Controller
@RequestMapping("/api/productos")
public class ProductoController {
	 @Autowired
	    private ProductRepository productoRepository;
	 
	 @GetMapping
	    public List<Producto> obtenerTodosLosProductos() {
	        return productoRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
	        Optional<Producto> producto = productoRepository.findById(id);
	        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public Producto crearNuevoProducto(@RequestBody Producto nuevoProducto) {
	        // TODO: Lógica para la creación de un nuevo producto
	        return productoRepository.save(nuevoProducto);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
	        // TODO: Lógica para actualizar un producto existente
	        Optional<Producto> productoExistente = productoRepository.findById(id);
	        if (productoExistente.isPresent()) {
	            Producto producto = productoExistente.get();
	            // Actualizar los campos necesarios
	            producto.setNombre(productoActualizado.getNombre());
	            //producto.setPrecio(productoActualizado.getPrecios());
	            return ResponseEntity.ok(productoRepository.save(producto));
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
	        // TODO: Lógica para eliminar un producto por ID
	        if (productoRepository.existsById(id)) {
	            productoRepository.deleteById(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
