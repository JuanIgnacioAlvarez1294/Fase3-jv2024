package com.mitienda.backoffice_gestion_productos.dto;

public class OrderItemDTO {

    private Long id;
    private Long productId;
    private Long orderId;
    private int quantity;
    private double price;

    // Constructor vacío
    public OrderItemDTO() {}

    // Constructor completo
    public OrderItemDTO(Long id, Long productId, Long orderId, int quantity, double price) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
