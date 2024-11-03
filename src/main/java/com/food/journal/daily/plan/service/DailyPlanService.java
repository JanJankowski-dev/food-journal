package com.food.journal.daily.plan.service;

import com.food.journal.daily.plan.DailyPlanRepository;
import com.food.journal.daily.plan.api.DailyPlanDto;
import com.food.journal.daily.plan.api.IngredientDto;
import com.food.journal.daily.plan.api.MealDto;
import com.food.journal.daily.plan.model.DailyPlan;
import com.food.journal.daily.plan.model.Ingredient;
import com.food.journal.daily.plan.model.Meal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DailyPlanService {

    private DailyPlanRepository dailyPlanRepository;

    public DailyPlanDto getPlan(LocalDate localDate) {
        return toDto(dailyPlanRepository.findByDate(localDate));
    }

    private DailyPlanDto toDto(DailyPlan dailyPlan) {
        return new DailyPlanDto(
                dailyPlan.getId(),
                dailyPlan.getDate(),
                dailyPlan.getMeals().stream().map(this::toDto).toList()
        );
    }

    private MealDto toDto(Meal it) {
        return new MealDto(
                it.getId(),
                it.getDescription(),
                it.getTime(),
                it.getName(),
                getIngredientsDto(it.getIngredients())
        );
    }

    private List<IngredientDto> getIngredientsDto(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(it -> new IngredientDto(
                        it.getId(),
                        it.getQuantity(),
                        it.getUnit(),
                        it.getName()))
                .toList();
    }
}
