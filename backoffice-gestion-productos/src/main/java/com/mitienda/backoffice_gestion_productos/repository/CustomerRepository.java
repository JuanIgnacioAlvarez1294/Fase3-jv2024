package com.mitienda.backoffice_gestion_productos.repository;

import com.mitienda.backoffice_gestion_productos.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Métodos personalizados pueden ser añadidos si es necesario
    // Ejemplo:
    // List<Customer> findByEmail(String email);
}
