package com.codingland.domain.quiz.service;

import com.codingland.domain.quiz.dto.ResponseDifficultyDto;
import com.codingland.domain.quiz.dto.ResponseDifficultyListDto;
import com.codingland.domain.quiz.entity.Difficulty;
import com.codingland.domain.quiz.entity.Quiz;
import com.codingland.domain.quiz.repository.DifficultyRepository;
import com.codingland.domain.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DifficultyService {
    private final DifficultyRepository difficultyRepository;
    private final QuizRepository quizRepository;

    /**
     *  난이도를 등록할 때 사용하는 메서드.
     *
     * @author 김원정
     * @param level 난이도
     */
    public void createDifficulty(int level) {
        Difficulty newDifficulty = new Difficulty(level);
        difficultyRepository.save(newDifficulty);
    }

    /**
     * 데이터베이스에 등록된 난이도를 모두 조회할 때 사용하는 메서드.
     * @author 김원정
     */
    public ResponseDifficultyListDto getDifficultyList() {
        List<Difficulty> difficultyList = difficultyRepository.findAll();
        List<ResponseDifficultyDto> difficultyDtoList = new ArrayList<>();
        for (Difficulty difficulty : difficultyList) {
            difficultyDtoList.add(
                    new ResponseDifficultyDto(
                            difficulty.getId(),
                            difficulty.getLevel()
                    )
            );
        }
        return new ResponseDifficultyListDto(difficultyDtoList);
    }

    /**
     * 난이도 수치를 조절할 때 사용하는 메서드입니다.
     * @author 김원정
     * @param difficulty_id 난이도 id
     * @param level 조절할 난이도 값
     * @throws RuntimeException
     */
    public void editDifficulty(Long difficulty_id, int level) {
        Difficulty foundDifficulty = difficultyRepository.findById(difficulty_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        foundDifficulty.updateDifficulty(level);
        difficultyRepository.save(foundDifficulty);
    }

    /**
     * 난이도를 삭제할 때 사용합니다. 퀴즈와 연관관계가 맺어진 난이도라면 삭제될 수 없습니다.
     * @author 김원정
     * @param difficulty_id 난이도 id
     * @throws RuntimeException
     */
    public void deleteDifficulty(Long difficulty_id) {
        Difficulty foundDifficulty = difficultyRepository.findById(difficulty_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        List<Quiz> foundQuizzes = quizRepository.findAllByDifficulty(foundDifficulty);
        if (!foundQuizzes.isEmpty()) {
            throw new RuntimeException("임시 Exception");
        }
        difficultyRepository.delete(foundDifficulty);
    }


}
