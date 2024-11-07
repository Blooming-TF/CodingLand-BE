package com.codingland.domain.quiz.service;

import com.codingland.domain.quiz.dto.ResponseIsQuizClearedDto;
import com.codingland.domain.quiz.dto.ResponseIsQuizClearedListDto;
import com.codingland.domain.quiz.entity.IsQuizCleared;
import com.codingland.domain.quiz.entity.Quiz;
import com.codingland.domain.quiz.repository.IsQuizClearedRepository;
import com.codingland.domain.quiz.repository.QuizRepository;
import com.codingland.domain.user.entity.User;
import com.codingland.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IsQuizClearedService {
    private final QuizRepository quizRepository;
    private final IsQuizClearedRepository isQuizClearedRepository;
    private final UserRepository userRepository;

    /**
     * 풀이가 완료된 퀴즈를 유저 정보와 함께 등록처리 합니다.
     * @author 김원정
     * @param quiz_id 퀴즈 id
     * @param user_id 유저 id
     * @throws RuntimeException
     */
    @Transactional
    public void solveProblem(Long quiz_id, Long user_id) {
            User foundUser = userRepository.findById(user_id)
                    .orElseThrow(() -> new RuntimeException("임시 Exception"));
            Quiz foundQuiz = quizRepository.findById(quiz_id)
                    .orElseThrow(() -> new RuntimeException("임시 Exception"));
            IsQuizCleared newIsQuizCleared = IsQuizCleared.thisProblemIsCleared(foundQuiz, foundUser);
            isQuizClearedRepository.save(newIsQuizCleared);
    }

    /**
     * 퀴즈 완료 여부를 단 건 조회하는 메서드입니다.
     * @author 김원정
     * @param isQuizCleared_id 퀴즈 완료 여부의 id
     * @throws RuntimeException
     */
    public ResponseIsQuizClearedDto getIsQuizCleared(Long isQuizCleared_id) {
        IsQuizCleared foundIsQuizCleared = isQuizClearedRepository.findById(isQuizCleared_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        return new ResponseIsQuizClearedDto(
                foundIsQuizCleared.getId(),
                foundIsQuizCleared.getUser().getUserId(),
                foundIsQuizCleared.getQuiz().getId(),
                foundIsQuizCleared.isCleared()
        );
    }

    /**
     * 데이터베이스에 등록된 퀴즈 완료 여부를 모두 조회하는 메서드입니다.
     * @author 김원정
     */
    public ResponseIsQuizClearedListDto getAllIsQuizCleared() {
        List<IsQuizCleared> isQuizClearedList = isQuizClearedRepository.findAll();
        List<ResponseIsQuizClearedDto> responseIsQuizClearedDtoList = new ArrayList<>();
        for (IsQuizCleared isQuizCleared : isQuizClearedList) {
            responseIsQuizClearedDtoList.add(
                    new ResponseIsQuizClearedDto(
                            isQuizCleared.getId(),
                            isQuizCleared.getUser().getUserId(),
                            isQuizCleared.getQuiz().getId(),
                            isQuizCleared.isCleared()
                    )
            );
        }
        return new ResponseIsQuizClearedListDto(responseIsQuizClearedDtoList);
    }

    /**
     * 퀴즈 완료 여부를 수정하는 메서드입니다.
     * @author 김원정
     * @param isQuizCleared_id 퀴즈 완료 여부 id
     * @param quiz_id 퀴즈의 id
     * @param user_id 유저의 id
     * @param is_cleared 완료 여부
     * @throws RuntimeException
     */
    public void editIsQuizCleared(Long isQuizCleared_id, Long quiz_id, Long user_id, boolean is_cleared) {
        IsQuizCleared foundIsQuizCleared = isQuizClearedRepository.findById(isQuizCleared_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        Quiz foundQuiz = quizRepository.findById(quiz_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        User foundUser = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));

        foundIsQuizCleared.editIsQuizCleared(is_cleared, foundQuiz, foundUser);
    }

}
