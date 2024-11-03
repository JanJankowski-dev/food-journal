package com.food.journal.daily.plan.api;

import com.food.journal.daily.plan.service.DailyPlanService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/food")
@AllArgsConstructor
public class FoodController {

    private final DailyPlanService dailyPlanService;
    @GetMapping()
    public String getFoodMap(JwtAuthenticationToken token) {
        return "food-map";
    }

    @GetMapping(path = "daily-plan")
    public DailyPlanDto getDailyPlan(){
        return dailyPlanService.getPlan(LocalDate.now());
    }
}
