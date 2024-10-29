package com.food.journal.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @GetMapping()
    public String getFoodMap() {
        return "food-map";
    }
}
