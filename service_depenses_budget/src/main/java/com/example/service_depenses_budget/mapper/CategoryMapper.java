package com.example.service_depenses_budget.mapper;

import com.example.service_depenses_budget.dto.CategoryDTO;
import com.example.service_depenses_budget.dto.ExpenseDTO;
import com.example.service_depenses_budget.entity.Category;
import com.example.service_depenses_budget.entity.Expense;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CategoryMapper {

    private final ModelMapper modelMapper;

    // Convertir une entité Category en CategoryDTO
    public CategoryDTO toDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    // Convertir un CategoryDTO en entité Category
    public Category toEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    public List<CategoryDTO> toDTO(List<Category> categories) {
        return categories.stream()
                .map(ca -> modelMapper.map(ca, CategoryDTO.class))
                .collect(Collectors.toList());
    }

}

