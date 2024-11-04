package com.mitienda.backoffice_gestion_productos.repository;

import com.mitienda.backoffice_gestion_productos.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Métodos personalizados pueden ser añadidos si es necesario
    // Ejemplo:
    // Category findByName(String name);
}

