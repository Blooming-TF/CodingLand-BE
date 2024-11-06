package com.codingland.domain.quiz.dto;

import java.util.List;

public record ResponseFindByManyQuizDto(
        List<ResponseFindByOneQuizDto> result
) {
}
