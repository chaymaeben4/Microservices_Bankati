package com.example.service_depenses_budget.mapper;

import com.example.service_depenses_budget.dto.BudgetDTO;
import com.example.service_depenses_budget.entity.Budget;
import com.example.service_depenses_budget.entity.Category;
import com.example.service_depenses_budget.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BudgetMapper {


    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    // Mapper une entité Budget en BudgetDTO
    public BudgetDTO toDTO(Budget budget) {
        BudgetDTO budgetDTO = modelMapper.map(budget, BudgetDTO.class);

        // Ajouter l'ID de la catégorie dans le DTO (au lieu de l'objet entier)
        if (budget.getCategory() != null) {
            budgetDTO.setCategoryId(budget.getCategory().getId());
        }
        return budgetDTO;

    }

    // Mapper un BudgetDTO en entité Budget
    public Budget toEntity(BudgetDTO budgetDTO) {
        Budget budget = modelMapper.map(budgetDTO, Budget.class);

        // Charger la catégorie à partir de l'ID dans le DTO et l'associer à l'entité Budget
        if (budgetDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(budgetDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            budget.setCategory(category);
        }
        return budget;
    }

    // Mapper une liste d'entités Budget en une liste de BudgetDTO
    public List<BudgetDTO> toDTO(List<Budget> budgets) {
        return budgets.stream()
                .map(budget -> modelMapper.map(budget, BudgetDTO.class))
                .collect(Collectors.toList());
    }
}
