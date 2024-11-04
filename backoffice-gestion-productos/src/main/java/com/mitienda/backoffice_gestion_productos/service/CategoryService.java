package com.mitienda.backoffice_gestion_productos.service;

import com.mitienda.backoffice_gestion_productos.dto.CategoryDTO;
import com.mitienda.backoffice_gestion_productos.model.Category;
import com.mitienda.backoffice_gestion_productos.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Convertir entidad Category a DTO
    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    // Convertir DTO a entidad Category
    private Category convertToEntity(CategoryDTO categoryDTO) {
        return new Category(
                categoryDTO.getName(),
                categoryDTO.getDescription()
        );
    }

    // Obtener todas las categorías
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una categoría por ID
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    // Crear una nueva categoría
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        return convertToDTO(categoryRepository.save(category));
    }

    // Actualizar una categoría
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());
        return convertToDTO(categoryRepository.save(existingCategory));
    }

    // Eliminar una categoría
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

