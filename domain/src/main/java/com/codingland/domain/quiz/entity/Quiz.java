package com.codingland.domain.quiz.entity;

import com.codingland.domain.chapter.entity.Chapter;
import com.codingland.domain.quiz.common.QuizTypeEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUIZ_ID")
    private Long id;
    private String question;
    private String answer;
    private QuizTypeEnum type;
    private String title;

    @ManyToOne
    @JoinColumn(name = "CHAPTER_ID")
    private Chapter chapter;

    @Builder
    public Quiz(String question, String answer, QuizTypeEnum type, String title, Chapter chapter) {
        this.question = question;
        this.answer = answer;
        this.type = type;
        this.title = title;
        this.chapter = chapter;
    }
}
