package com.food.journal.daily.plan.api;

import java.time.LocalDate;
import java.util.List;

public record DailyPlanDto(Long id, LocalDate date, List<MealDto> meals) {
}

