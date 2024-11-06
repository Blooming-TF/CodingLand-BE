package com.codingland.domain.quiz.dto;

import com.codingland.domain.quiz.common.QuizTypeEnum;
import lombok.Builder;

@Builder
public record ResponseFindByOneQuizDto(
        Long quizId,
        String question,
        String answer,
        QuizTypeEnum type,
        String title,
        Long chapterId
) {
}
