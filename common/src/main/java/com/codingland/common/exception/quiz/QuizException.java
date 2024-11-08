package com.codingland.common.exception.quiz;

import com.codingland.common.common.BaseErrorCode;
import lombok.Getter;

@Getter
public class QuizException extends RuntimeException {
    private final BaseErrorCode errorCode;
    private final Throwable cause;

    public QuizException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
        this.cause = null;
    }
}