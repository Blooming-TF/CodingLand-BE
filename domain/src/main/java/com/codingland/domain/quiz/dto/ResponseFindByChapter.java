package com.codingland.domain.quiz.dto;

import com.codingland.domain.quiz.common.BlockTypeEnum;
import lombok.Builder;

@Builder
public record ResponseFindByChapter(
        Long quizId,
        BlockTypeEnum type,
        String title,
        int level,
        boolean isCleared
) {
}
