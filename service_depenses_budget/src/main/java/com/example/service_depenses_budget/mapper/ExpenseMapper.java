package com.example.service_depenses_budget.mapper;

import com.example.service_depenses_budget.dto.BudgetDTO;
import com.example.service_depenses_budget.dto.ExpenseDTO;
import com.example.service_depenses_budget.entity.Expense;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ExpenseMapper {

    private final ModelMapper modelMapper;

    // Convertir une entité Expense en ExpenseDTO
    public ExpenseDTO toDTO(Expense expense) {
        return modelMapper.map(expense, ExpenseDTO.class);
    }

    // Convertir un ExpenseDTO en entité Expense
    public Expense toEntity(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, Expense.class);
    }
    public List<ExpenseDTO> toDTO(List<Expense> expenses) {
        return expenses.stream()
                .map(expense -> modelMapper.map(expense, ExpenseDTO.class))
                .collect(Collectors.toList());
    }
}

