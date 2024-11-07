package com.codingland.domain.quiz.service;

import com.codingland.domain.chapter.entity.Chapter;
import com.codingland.domain.chapter.repository.ChapterRepository;
import com.codingland.domain.quiz.dto.*;
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
public class QuizService {
    private final QuizRepository quizRepository;
    private final ChapterRepository chapterRepository;
    private final DifficultyRepository difficultyRepository;

    /**
     * 퀴즈 생성 요청을 처리하는 메서드.
     *
     * @author 김원정
     * @param requestCreateQuizDto 퀴즈 생성 요청 DTO
     * @throws RuntimeException
     */
    public void createQuiz(RequestCreateQuizDto requestCreateQuizDto) {
        Chapter foundChapter = chapterRepository.findById(requestCreateQuizDto.chapterId())
                .orElse(null);
        Difficulty difficulty = difficultyRepository.findByLevel(requestCreateQuizDto.level())
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        Quiz newQuiz = Quiz.builder()
                .type(requestCreateQuizDto.type())
                .title(requestCreateQuizDto.title())
                .answer(requestCreateQuizDto.answer())
                .chapter(foundChapter)
                .question(requestCreateQuizDto.question())
                .difficulty(difficulty)
                .build();
        quizRepository.save(newQuiz);
    }

    /**
     * 주어진 ID에 해당하는 퀴즈를 조회하여 반환합니다.
     *
     * @author 김원정
     * @param quiz_id 조회할 퀴즈의 ID
     * @return 조회된 퀴즈 정보를 담은 ResponseFindQuizDto 객체
     * @throws RuntimeException 퀴즈를 찾지 못했을 경우 발생하는 예외
     */
    public ResponseQuizDto findByOne(Long quiz_id) {
        Quiz foundQuiz = quizRepository.findById(quiz_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        return ResponseQuizDto.builder()
                .quizId(foundQuiz.getId())
                .chapterId(foundQuiz.getChapter().getId())
                .question(foundQuiz.getQuestion())
                .answer(foundQuiz.getAnswer())
                .title(foundQuiz.getTitle())
                .type(foundQuiz.getType())
                .level(foundQuiz.getDifficulty().getLevel())
                .build();
    }

    /**
     * 데이터베이스에 등록된 모든 Quiz 정보를 가져오는 메서드
     *
     * @author 김원정
     * @return 조회된 Quiz들의 정보를 담고 있는 ResponseQuizListDto
     */
    public ResponseQuizListDto findByMany() {
        List<Quiz> quizList = quizRepository.findAll();
        List<ResponseQuizDto> responseQuizDtoList = new ArrayList<>();
        for (Quiz quiz : quizList) {
            responseQuizDtoList.add(
                    ResponseQuizDto.builder()
                            .quizId(quiz.getId())
                            .title(quiz.getTitle())
                            .type(quiz.getType())
                            .chapterId(quiz.getChapter().getId())
                            .answer(quiz.getAnswer())
                            .question(quiz.getQuestion())
                            .level(quiz.getDifficulty().getLevel())
                            .build()
            );
        }
        return new ResponseQuizListDto(responseQuizDtoList);
    }


    /**
     * 특정 Chapter에 속한 Quiz들을 조회하는 메서드
     *
     * @author 김원정
     * @param chapterId 조회할 Chapter id
     * @return Quiz들의 정보가 담긴 ResponseFindByChapter
     */
    public ResponseFindByChapterListDto findQuizzesInChapter(Long chapterId) {
        Chapter foundChapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        List<Quiz> quizList = quizRepository.findAll();
        List<ResponseFindByChapter> responseFindByChapters = new ArrayList<>();
        for (Quiz quiz : quizList) {
            if (quiz.getChapter().getId().equals(foundChapter.getId())) {
                responseFindByChapters.add(
                        ResponseFindByChapter.builder()
                                .quizId(quiz.getId())
                                .title(quiz.getTitle())
                                .type(quiz.getType())
                                .level(quiz.getDifficulty().getLevel())
                                .build()
                );
            }
        }
        return new ResponseFindByChapterListDto(responseFindByChapters);
    }

    /**
     * 특정 Quiz를 수정하거나 Chapter와의 연관관계를 수정하고 싶을 때 사용하는 메서드입니다.
     *
     * @author 김원정
     * @param requestEditQuizDto 수정할 정보를 담고 있는 RequestEditQuizDto
     * @throws RuntimeException
     */
    public void editQuiz(RequestEditQuizDto requestEditQuizDto) {
        Quiz foundQuiz = quizRepository.findById(requestEditQuizDto.chapterId())
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        Chapter foundChapter = chapterRepository.findById(requestEditQuizDto.chapterId())
                .orElse(null);
        Difficulty foundDifficulty = difficultyRepository.findByLevel(requestEditQuizDto.level())
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        foundQuiz.updateQuizByDto(requestEditQuizDto, foundChapter, foundDifficulty);
        quizRepository.save(foundQuiz);
    }

    /**
     * 퀴즈를 삭제할 때 사용하는 메서드입니다.
     *
     * @author 김원정
     * @param quiz_id 삭제하고 싶은 quiz의 id
     * @throws RuntimeException
     */
    public void deleteQuiz(Long quiz_id) {
        Quiz foundQuiz = quizRepository.findById(quiz_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        quizRepository.delete(foundQuiz);
    }
}
