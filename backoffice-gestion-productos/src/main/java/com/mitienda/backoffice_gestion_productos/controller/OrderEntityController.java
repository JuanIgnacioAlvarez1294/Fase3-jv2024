package com.mitienda.backoffice_gestion_productos.controller;

import com.mitienda.backoffice_gestion_productos.dto.OrderEntityDTO;
import com.mitienda.backoffice_gestion_productos.service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
public class OrderEntityController {

    @Autowired
    private OrderEntityService orderEntityService;

    @GetMapping
    public List<OrderEntityDTO> getAllOrders() {
        return orderEntityService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderEntityDTO getOrderById(@PathVariable Long id) {
        return orderEntityService.getOrderById(id);
    }

    @PostMapping
    public OrderEntityDTO createOrder(@RequestBody OrderEntityDTO orderDTO) {
        return orderEntityService.createOrder(orderDTO);
    }

    @PutMapping("/{id}")
    public OrderEntityDTO updateOrder(@PathVariable Long id, @RequestBody OrderEntityDTO orderDTO) {
        return orderEntityService.updateOrder(id, orderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderEntityService.deleteOrder(id);
    }
}
