package com.mitienda.backoffice_gestion_productos.controller;

import com.mitienda.backoffice_gestion_productos.model.OrderEntity;
import com.mitienda.backoffice_gestion_productos.service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private OrderEntityService orderEntityService;

    // Obtener el carrito actual del cliente
    @GetMapping("/{customerId}")
    public ResponseEntity<OrderEntity> getCart(@PathVariable Long customerId) {
        OrderEntity cart = orderEntityService.getOrCreateCart(customerId);
        return ResponseEntity.ok(cart);
    }

    // Agregar un producto al carrito
    @PostMapping("/{customerId}/add")
    public ResponseEntity<OrderEntity> addToCart(@PathVariable Long customerId, @RequestParam Long productId, @RequestParam int quantity) {
        OrderEntity updatedCart = orderEntityService.addToCart(customerId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    // Completar el carrito
    @PostMapping("/{customerId}/complete")
    public ResponseEntity<OrderEntity> completeOrder(@PathVariable Long customerId) {
        OrderEntity completedOrder = orderEntityService.completeOrder(customerId);
        return ResponseEntity.ok(completedOrder);
    }

    @DeleteMapping("/{customerId}/remove")
    public ResponseEntity<OrderEntity> removeFromCart(@PathVariable Long customerId, @RequestParam Long productId) {
        OrderEntity updatedCart = orderEntityService.removeFromCart(customerId, productId);
        return ResponseEntity.ok(updatedCart);
    }
}
