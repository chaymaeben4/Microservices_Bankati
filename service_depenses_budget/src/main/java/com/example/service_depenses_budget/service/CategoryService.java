package com.example.service_depenses_budget.service;

import com.example.service_depenses_budget.dto.CategoryDTO;
import com.example.service_depenses_budget.entity.Category;
import com.example.service_depenses_budget.repository.CategoryRepository;
import com.example.service_depenses_budget.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    // Crée une nouvelle catégorie
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    // Récupère une catégorie par son ID
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toDTO(category);
    }

    // Met à jour une catégorie existante
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    // Supprime une catégorie par son ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // Récupère toutes les catégories
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDTO(categories);
    }
}
