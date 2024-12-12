package com.example.service_depenses_budget.service;

import com.example.service_depenses_budget.entity.Budget;
import com.example.service_depenses_budget.entity.Category;
import com.example.service_depenses_budget.entity.Expense;
import com.example.service_depenses_budget.repository.BudgetRepository;
import com.example.service_depenses_budget.repository.CategoryRepository;
import com.example.service_depenses_budget.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseCategorizationService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final BudgetRepository budgetRepository;

    // Mots-clés par catégorie
    private final List<String> FOOD_KEYWORDS = Arrays.asList("restaurant", "supermarché", "épicerie", "café", "fast food");
    private final List<String> RENT_KEYWORDS = Arrays.asList("loyer", "appartement", "maison", "colocation");
    private final List<String> LEISURE_KEYWORDS = Arrays.asList("cinéma", "concert", "vacances", "sport", "hôtel");
    private final List<String> TRANSPORT_KEYWORDS = Arrays.asList("essence", "carburant", "taxi", "vélo", "voiture");
    private final List<String> HEALTH_KEYWORDS = Arrays.asList("médicaments", "consultation", "hôpital", "dentaire");
    private final List<String> EDUCATION_KEYWORDS = Arrays.asList("école", "université", "formation", "cours", "livres");
    private final List<String> SHOPPING_KEYWORDS = Arrays.asList("vêtements", "chaussures", "électronique", "accessoires");
    private final List<String> OTHER_KEYWORDS = Arrays.asList("cadeau", "impôt", "prêt");

    // Mots-clés pour les nouvelles catégories
    private final List<String> ENTERTAINMENT_KEYWORDS = Arrays.asList("cinéma", "concert", "spectacle", "théâtre", "musique", "soirée", "festival");
    private final List<String> UTILITIES_KEYWORDS = Arrays.asList("électricité", "eau", "gaz", "chauffage", "internet", "téléphone", "service public");
    private final List<String> TRAVEL_KEYWORDS = Arrays.asList("vol", "billet d'avion", "hôtel", "voyage", "vacances", "location de voiture", "transport");
    private final List<String> INSURANCE_KEYWORDS = Arrays.asList("assurance", "santé", "voiture", "maison", "vie", "prévoyance", "responsabilité civile");


    // Méthode pour catégoriser une dépense
    public Expense categorizeExpense(Expense expense) {
        // Étape 1 : Déterminer la catégorie à partir de la description
        Category category = categorizeByDescription(expense.getDescription());
        if (category == null) {
            throw new IllegalArgumentException("La dépense ne peut pas être catégorisée. Description invalide.");
        }
        expense.setCategory(category);

        // Étape 2 : Trouver un budget actif correspondant à la catégorie et à l'utilisateur
        Budget budget = findActiveBudgetForCategory(category, expense.getUserId());
        if (budget == null) {
            throw new IllegalArgumentException("Aucun budget actif ne correspond à la catégorie détectée.");
        }
        expense.setBudget(budget);

        // Étape 3 : Sauvegarder la dépense avec sa catégorie et son budget
        return expenseRepository.save(expense);
    }


    // Logique de catégorisation par description
    private Category categorizeByDescription(String description) {
        // Convertir la description en minuscules pour éviter les erreurs de casse
        String lowerCaseDescription = description.toLowerCase();

// Logique de catégorisation
        if (containsKeyword(lowerCaseDescription, FOOD_KEYWORDS)) {
            return categoryRepository.findByName("Alimentation");
        } else if (containsKeyword(lowerCaseDescription, RENT_KEYWORDS)) {
            return categoryRepository.findByName("Loyer");
        } else if (containsKeyword(lowerCaseDescription, LEISURE_KEYWORDS)) {
            return categoryRepository.findByName("Loisirs");
        } else if (containsKeyword(lowerCaseDescription, TRANSPORT_KEYWORDS)) {
            return categoryRepository.findByName("Transport");
        } else if (containsKeyword(lowerCaseDescription, HEALTH_KEYWORDS)) {
            return categoryRepository.findByName("Santé");
        } else if (containsKeyword(lowerCaseDescription, EDUCATION_KEYWORDS)) {
            return categoryRepository.findByName("Éducation");
        } else if (containsKeyword(lowerCaseDescription, SHOPPING_KEYWORDS)) {
            return categoryRepository.findByName("Shopping");
        } else if (containsKeyword(lowerCaseDescription, OTHER_KEYWORDS)) {
            return categoryRepository.findByName("Autres");
        } else if (containsKeyword(lowerCaseDescription, ENTERTAINMENT_KEYWORDS)) {  // Nouvelle catégorie
            return categoryRepository.findByName("Divertissement");
        } else if (containsKeyword(lowerCaseDescription, UTILITIES_KEYWORDS)) {  // Nouvelle catégorie
            return categoryRepository.findByName("Services publics");
        } else if (containsKeyword(lowerCaseDescription, TRAVEL_KEYWORDS)) {  // Nouvelle catégorie
            return categoryRepository.findByName("Voyages");
        } else if (containsKeyword(lowerCaseDescription, INSURANCE_KEYWORDS)) {  // Nouvelle catégorie
            return categoryRepository.findByName("Assurance");
        } else {
            return categoryRepository.findByName("Autres");
        }
    }

    // Vérifie si un mot-clé est présent dans la description
    private boolean containsKeyword(String description, List<String> keywords) {
        for (String keyword : keywords) {
            if (description.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private Budget findActiveBudgetForCategory(Category category, Long userId) {
        // Rechercher un budget pour la catégorie et l'utilisateur
        return budgetRepository.findByCategoryAndUserId(category, userId)
                .stream()
                .findFirst() // Retourner le premier budget trouvé
                .orElse(null); // Retourne null si aucun budget n'est trouvé
    }

}
