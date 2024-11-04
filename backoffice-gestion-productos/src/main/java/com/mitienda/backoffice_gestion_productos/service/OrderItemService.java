package com.mitienda.backoffice_gestion_productos.service;

import com.mitienda.backoffice_gestion_productos.dto.OrderItemDTO;
import com.mitienda.backoffice_gestion_productos.model.OrderItem;
import com.mitienda.backoffice_gestion_productos.model.Product;
import com.mitienda.backoffice_gestion_productos.repository.OrderItemRepository;
import com.mitienda.backoffice_gestion_productos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // Convertir entidad OrderItem a DTO
    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getProduct().getId(),
                orderItem.getOrderEntity().getId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }

    private OrderItem convertToEntity(OrderItemDTO orderItemDTO) {
        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return new OrderItem(
                product,
                null,  // El pedido se debe establecer en OrderService cuando se maneje el pedido completo
                orderItemDTO.getQuantity(),
                orderItemDTO.getPrice()
        );
    }

    // Obtener todos los items de un pedido
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener un item de pedido por ID
    public OrderItemDTO getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Item de pedido no encontrado"));
    }

    // Crear un nuevo item de pedido
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = convertToEntity(orderItemDTO);
        return convertToDTO(orderItemRepository.save(orderItem));
    }

    // Actualizar un item de pedido
    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) {
        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de pedido no encontrado"));
        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existingOrderItem.setProduct(product);
        existingOrderItem.setQuantity(orderItemDTO.getQuantity());
        existingOrderItem.setPrice(orderItemDTO.getPrice());
        return convertToDTO(orderItemRepository.save(existingOrderItem));
    }

    // Eliminar un item de pedido
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}

