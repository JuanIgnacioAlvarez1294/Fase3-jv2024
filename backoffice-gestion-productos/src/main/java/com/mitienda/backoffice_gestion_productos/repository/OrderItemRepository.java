package com.mitienda.backoffice_gestion_productos.repository;

import com.mitienda.backoffice_gestion_productos.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Métodos personalizados pueden ser añadidos si es necesario
    // Ejemplo:
    // List<OrderItem> findByOrderId(Long orderId);
}
