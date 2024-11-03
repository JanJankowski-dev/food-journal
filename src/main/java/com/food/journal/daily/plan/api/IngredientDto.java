package com.food.journal.daily.plan.api;

import com.food.journal.daily.plan.model.Unit;

public record IngredientDto(Long id, double quantity, Unit unit, String name) {
}
