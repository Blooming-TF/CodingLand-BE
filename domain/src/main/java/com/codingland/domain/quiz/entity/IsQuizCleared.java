package com.codingland.domain.quiz.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IsQuizCleared {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isCleared;

    @ManyToOne
    @JoinColumn(name = "QUIZ_ID")
    private Quiz quiz;
}
