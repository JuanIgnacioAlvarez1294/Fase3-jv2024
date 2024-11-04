package com.mitienda.backoffice_gestion_productos.service;

import com.mitienda.backoffice_gestion_productos.dto.CustomerDTO;
import com.mitienda.backoffice_gestion_productos.model.Customer;
import com.mitienda.backoffice_gestion_productos.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Convertir entidad Customer a DTO
    private CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }

    // Convertir DTO a entidad Customer
    private Customer convertToEntity(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getEmail(),
                customerDTO.getPhone()
        );
    }

    // Obtener todos los clientes
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener un cliente por ID
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    // Crear un nuevo cliente
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        return convertToDTO(customerRepository.save(customer));
    }

    // Actualizar un cliente
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        existingCustomer.setFirstName(customerDTO.getFirstName());
        existingCustomer.setLastName(customerDTO.getLastName());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setPhone(customerDTO.getPhone());
        return convertToDTO(customerRepository.save(existingCustomer));
    }

    // Eliminar un cliente
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

