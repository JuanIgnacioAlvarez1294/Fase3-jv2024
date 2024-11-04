package com.mitienda.backoffice_gestion_productos.repository;

import com.mitienda.backoffice_gestion_productos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Aquí puedes agregar métodos personalizados si lo necesitas
    // Ejemplo:
    // List<Product> findByCategoryName(String categoryName);
}

