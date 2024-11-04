package com.mitienda.backoffice_gestion_productos.service;

import com.mitienda.backoffice_gestion_productos.dto.OrderEntityDTO;
import com.mitienda.backoffice_gestion_productos.model.Customer;
import com.mitienda.backoffice_gestion_productos.model.OrderEntity;
import com.mitienda.backoffice_gestion_productos.model.OrderItem;
import com.mitienda.backoffice_gestion_productos.model.Product;
import com.mitienda.backoffice_gestion_productos.repository.CustomerRepository;
import com.mitienda.backoffice_gestion_productos.repository.OrderEntityRepository;
import com.mitienda.backoffice_gestion_productos.repository.OrderItemRepository;
import com.mitienda.backoffice_gestion_productos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderEntityService {

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Convert OrderEntity to OrderEntityDTO
    private OrderEntityDTO convertToDTO(OrderEntity orderEntity) {
        return new OrderEntityDTO(
                orderEntity.getId(),
                orderEntity.getCustomer().getId(),
                orderEntity.getOrderDate(),
                orderEntity.getTotalPrice()
        );
    }

    // Convert OrderEntityDTO to OrderEntity
    private OrderEntity convertToEntity(OrderEntityDTO orderEntityDTO) {
        Customer customer = customerRepository.findById(orderEntityDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customer);
        orderEntity.setOrderDate(orderEntityDTO.getOrderDate());
        orderEntity.setTotalPrice(orderEntityDTO.getTotalPrice());
        return orderEntity;
    }

    // Get all orders
    public List<OrderEntityDTO> getAllOrders() {
        return orderEntityRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get order by ID
    public OrderEntityDTO getOrderById(Long id) {
        return orderEntityRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    // Create a new order
    public OrderEntityDTO createOrder(OrderEntityDTO orderEntityDTO) {
        OrderEntity orderEntity = convertToEntity(orderEntityDTO);
        OrderEntity savedOrder = orderEntityRepository.save(orderEntity);
        return convertToDTO(savedOrder);
    }

    // Update an existing order
    public OrderEntityDTO updateOrder(Long id, OrderEntityDTO orderEntityDTO) {
        OrderEntity existingOrder = orderEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Customer customer = customerRepository.findById(orderEntityDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        existingOrder.setCustomer(customer);
        existingOrder.setOrderDate(orderEntityDTO.getOrderDate());
        existingOrder.setTotalPrice(orderEntityDTO.getTotalPrice());

        return convertToDTO(orderEntityRepository.save(existingOrder));
    }

    // Delete an order
    public void deleteOrder(Long id) {
        orderEntityRepository.deleteById(id);
    }

    // Obtener o crear el carrito (OrderEntity) del usuario
    public OrderEntity getOrCreateCart(Long customerId) {
        Optional<OrderEntity> cart = orderEntityRepository.findByCustomerIdAndCompletedFalse(customerId);
        return cart.orElseGet(() -> {
            OrderEntity newCart = new OrderEntity();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            newCart.setCustomer(customer);
            newCart.setTotalPrice(0);
            newCart.setOrderDate(LocalDateTime.now());
            newCart.setCompleted(false); // El carrito no está completado inicialmente
            return orderEntityRepository.save(newCart);
        });
    }

    // Método para agregar un producto al carrito
    public OrderEntity addToCart(Long customerId, Long productId, int quantity) {
        OrderEntity cart = getOrCreateCart(customerId);

        // Buscar producto
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Crear y agregar OrderItem al carrito
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderEntity(cart);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(product.getPrice() * quantity);
        orderItemRepository.save(orderItem);

        // Actualizar el precio total del carrito
        cart.setTotalPrice(cart.getTotalPrice() + orderItem.getPrice());
        return orderEntityRepository.save(cart);
    }

    // Método para completar el pedido
    public OrderEntity completeOrder(Long customerId) {
        OrderEntity cart = getOrCreateCart(customerId);
        cart.setCompleted(true); // Marca el carrito como completado
        cart.setOrderDate(LocalDateTime.now()); // Establece la fecha de finalización
        return orderEntityRepository.save(cart);
    }

    // OrderEntityService.java

    public OrderEntity removeFromCart(Long customerId, Long productId) {
        OrderEntity cart = getOrCreateCart(customerId);

        // Logic to remove the OrderItem from the cart
        List<OrderItem> items = cart.getOrderItems(); // Assuming getItems() returns the list of OrderItems
        OrderItem itemToRemove = items.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        items.remove(itemToRemove); // Remove the item from the list
        cart.setTotalPrice(cart.getTotalPrice() - itemToRemove.getPrice()); // Update total price
        orderItemRepository.delete(itemToRemove); // Remove from the database
        return orderEntityRepository.save(cart); // Save changes to the cart
    }

}

