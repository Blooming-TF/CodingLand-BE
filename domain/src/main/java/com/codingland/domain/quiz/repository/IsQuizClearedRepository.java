package com.codingland.domain.quiz.repository;

import com.codingland.domain.quiz.entity.IsQuizCleared;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsQuizClearedRepository extends JpaRepository<IsQuizCleared, Long> {
}
