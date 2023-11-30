package com.codeup.codeupspringblog.jpa_lectures.repositories;

import com.codeup.codeupspringblog.jpa_lectures.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;


//datatype name and datatype of primary key <Food, Long>
public interface FoodRepository extends JpaRepository<Food, Long> {
}
