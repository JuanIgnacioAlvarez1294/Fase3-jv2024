package com.mitienda.backoffice_gestion_productos.repository;

import com.mitienda.backoffice_gestion_productos.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    // Métodos personalizados pueden ser añadidos si es necesario
    // Ejemplo:
    // List<Supplier> findByName(String name);
}

