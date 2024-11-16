package com.food.journal.daily.plan.model;

import com.food.journal.daily.plan.api.MealDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "meal")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;

    @ManyToOne
    @JoinColumn(name = "daily_plan_id", nullable = false)
    private DailyPlan dailyPlan;

}
