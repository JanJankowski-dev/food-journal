package com.food.journal.daily.plan.api;

import com.food.journal.daily.plan.service.DailyPlanService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/daily-plan")
@AllArgsConstructor
public class DailyPlanController {

    private final DailyPlanService dailyPlanService;
    private final KeycloakUserService keycloakUserService;

    @GetMapping("{date}")
    public DailyPlanDto getDailyPlan(@PathVariable LocalDate date, JwtAuthenticationToken token){
        String userId = keycloakUserService.getUserId(token);
        return dailyPlanService.getPlan(date, userId);
    }

    @PostMapping(path = "{id}/meal")
    public void addMeal(@PathVariable Integer id, @RequestBody MealDto mealDto){
        dailyPlanService.addMeal(id, mealDto);
    }
}
