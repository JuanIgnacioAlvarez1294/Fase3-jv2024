package com.mitienda.backoffice_gestion_productos.service;

import com.mitienda.backoffice_gestion_productos.dto.SupplierDTO;
import com.mitienda.backoffice_gestion_productos.model.Supplier;
import com.mitienda.backoffice_gestion_productos.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    // Convertir entidad Supplier a DTO
    private SupplierDTO convertToDTO(Supplier supplier) {
        return new SupplierDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getContactEmail(),
                supplier.getContactPhone()
        );
    }

    // Convertir DTO a entidad Supplier
    private Supplier convertToEntity(SupplierDTO supplierDTO) {
        return new Supplier(
                supplierDTO.getName(),
                supplierDTO.getContactEmail(),
                supplierDTO.getContactPhone()
        );
    }

    // Obtener todos los proveedores
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener un proveedor por ID
    public SupplierDTO getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    // Crear un nuevo proveedor
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = convertToEntity(supplierDTO);
        return convertToDTO(supplierRepository.save(supplier));
    }

    // Actualizar un proveedor
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        existingSupplier.setName(supplierDTO.getName());
        existingSupplier.setContactEmail(supplierDTO.getContactEmail());
        existingSupplier.setContactPhone(supplierDTO.getContactPhone());
        return convertToDTO(supplierRepository.save(existingSupplier));
    }

    // Eliminar un proveedor
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
