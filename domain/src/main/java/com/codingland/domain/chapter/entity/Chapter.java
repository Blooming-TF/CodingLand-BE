package com.codingland.domain.chapter.entity;

import com.codingland.domain.quiz.entity.Quiz;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAPTER_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "chapter")
    private List<Quiz> quizzes = new ArrayList<>();
}
