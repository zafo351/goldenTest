package com.repository.test;
import org.springframework.data.jpa.repository.JpaRepository;

import com.producto.test.Producto;

public interface ProductRepository extends JpaRepository<Producto, Long> {
	
	//TODO: Actualizar con metodos personalizados.
}
