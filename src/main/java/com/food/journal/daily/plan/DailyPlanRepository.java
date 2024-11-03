package com.food.journal.daily.plan;

import com.food.journal.daily.plan.model.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DailyPlanRepository extends JpaRepository<DailyPlan, Integer> {

    DailyPlan findByDate(LocalDate date);

}
