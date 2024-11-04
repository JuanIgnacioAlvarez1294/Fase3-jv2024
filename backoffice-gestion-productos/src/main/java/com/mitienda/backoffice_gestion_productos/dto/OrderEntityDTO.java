package com.mitienda.backoffice_gestion_productos.dto;

import java.time.LocalDateTime;

public class OrderEntityDTO {

    private Long id;
    private Long customerId;
    private LocalDateTime orderDate;
    private double totalPrice;

    // Constructor vacío
    public OrderEntityDTO() {}

    // Constructor completo
    public OrderEntityDTO(Long id, Long customerId, LocalDateTime orderDate, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
