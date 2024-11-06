package com.codingland.domain.quiz.repository;

import com.codingland.domain.quiz.entity.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DifficultyRepository extends JpaRepository<Difficulty, Long> {
}
