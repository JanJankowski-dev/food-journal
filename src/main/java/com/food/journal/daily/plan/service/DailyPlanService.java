package com.food.journal.daily.plan.service;

import com.food.journal.daily.plan.DailyPlanRepository;
import com.food.journal.daily.plan.api.DailyPlanDto;
import com.food.journal.daily.plan.api.IngredientDto;
import com.food.journal.daily.plan.api.MealDto;
import com.food.journal.daily.plan.model.DailyPlan;
import com.food.journal.daily.plan.model.MealIngredient;
import com.food.journal.daily.plan.model.Meal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                getIngredientsDto(it.getMealIngredients())
        );
    }

    private List<IngredientDto> getIngredientsDto(List<MealIngredient> mealIngredients) {
        return mealIngredients.stream()
                .map(it -> new IngredientDto(
                        it.getId(),
                        it.getQuantity(),
                        it.getUnit(),
                        it.getName()))
                .toList();
    }

    public void addMeal(Integer id, MealDto mealDto) {
        Optional<DailyPlan> plan = dailyPlanRepository.findById(id);
        plan.ifPresent(dailyPlan -> dailyPlan.getMeals().add(mapToEntity(mealDto, dailyPlan)));

        dailyPlanRepository.save(plan.get());
    }

    public Meal mapToEntity(MealDto mealDto, DailyPlan dailyPlan) {
        if (mealDto == null) {
            return null;
        }
        Meal meal = new Meal();
        meal.setDescription(mealDto.description());
        meal.setTime(mealDto.time());
        meal.setName(mealDto.name());
        meal.setDailyPlan(dailyPlan);

        if (mealDto.ingredients() != null) {
            List<MealIngredient> mealIngredients = mealDto.ingredients().stream()
                    .map(this::mapToEntity)
                    .collect(Collectors.toList());
            meal.setMealIngredients(mealIngredients);
            for (MealIngredient mealIngredient : mealIngredients) {
                mealIngredient.setMeal(meal);
            }
        }
        return meal;
    }

    public MealIngredient mapToEntity(IngredientDto ingredientDto) {
        if (ingredientDto == null) {
            return null;
        }
        MealIngredient mealIngredient = new MealIngredient();
        mealIngredient.setQuantity(ingredientDto.quantity());
        mealIngredient.setUnit(ingredientDto.unit());
        mealIngredient.setName(ingredientDto.name());
        return mealIngredient;
    }
}
