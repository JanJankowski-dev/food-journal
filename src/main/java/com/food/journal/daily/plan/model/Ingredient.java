package com.food.journal.daily.plan.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private double quantity;

    @Column(name = "unit", nullable = false)
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;
}
