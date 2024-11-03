package com.food.journal.daily.plan.api;

import java.time.LocalTime;
import java.util.List;

public record MealDto(Long id, String description, LocalTime time, String name, List<IngredientDto> ingredients) {
}
