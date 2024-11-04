package com.mitienda.backoffice_gestion_productos.service;

import com.mitienda.backoffice_gestion_productos.dto.ProductDTO;
import com.mitienda.backoffice_gestion_productos.model.Category;
import com.mitienda.backoffice_gestion_productos.model.Product;
import com.mitienda.backoffice_gestion_productos.model.Supplier;
import com.mitienda.backoffice_gestion_productos.repository.CategoryRepository;
import com.mitienda.backoffice_gestion_productos.repository.ProductRepository;
import com.mitienda.backoffice_gestion_productos.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    // Convertir entidad Product a DTO
    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory() != null ? product.getCategory().getId() : null,  // Obtener ID de Category
                product.getSupplier() != null ? product.getSupplier().getId() : null   // Obtener ID de Supplier
        );
    }

    // Convertir DTO a entidad Product
    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        // Verificar que categoryId y supplierId no sean nulos, y buscar las entidades
        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            product.setCategory(category);
        }

        if (productDTO.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(productDTO.getSupplierId())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
            product.setSupplier(supplier);
        }

        return product;
    }

    // Obtener todos los productos
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener un producto por ID
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Crear un nuevo producto
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        return convertToDTO(productRepository.save(product));
    }

    // Actualizar un producto
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setStock(productDTO.getStock());

        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            existingProduct.setCategory(category);
        }

        if (productDTO.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(productDTO.getSupplierId())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
            existingProduct.setSupplier(supplier);
        }

        return convertToDTO(productRepository.save(existingProduct));
    }

    // Eliminar un producto
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
