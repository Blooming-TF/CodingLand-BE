package com.codingland.domain.quiz.entity;

import com.codingland.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IsQuizCleared {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISQUIZCLEARED_ID")
    private Long id;
    @ColumnDefault("false")
    private boolean isCleared;

    @ManyToOne
    @JoinColumn(name = "QUIZ_ID")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "USER_USERID")
    private User user;

    public IsQuizCleared(boolean isCleared, Quiz quiz, User user) {
        this.isCleared = isCleared;
        this.quiz = quiz;
        this.user = user;
    }

    public static IsQuizCleared thisProblemIsCleared(Quiz quiz, User user) {
        return new IsQuizCleared(true, quiz, user);
    }

    public static IsQuizCleared thisProblemIsNotCleared(Quiz quiz, User user) {
        return new IsQuizCleared(false, quiz, user);
    }

    public void editIsQuizCleared(boolean isCleared, Quiz quiz, User user) {
        this.isCleared = isCleared;
        this.quiz = quiz;
        this.user = user;
    }
}
