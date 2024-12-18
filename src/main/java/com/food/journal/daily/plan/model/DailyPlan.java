package com.food.journal.daily.plan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@Setter
@Table(name = "daily_plan")

public class DailyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="userId", nullable = false)
    private String userId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "dailyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals;

}

