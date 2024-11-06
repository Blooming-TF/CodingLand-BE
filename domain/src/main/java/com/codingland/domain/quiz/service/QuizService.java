package com.codingland.domain.quiz.service;

import com.codingland.domain.chapter.entity.Chapter;
import com.codingland.domain.chapter.repository.ChapterRepository;
import com.codingland.domain.quiz.dto.RequestCreateQuizDto;
import com.codingland.domain.quiz.dto.ResponseFindByManyQuizDto;
import com.codingland.domain.quiz.dto.ResponseFindByOneQuizDto;
import com.codingland.domain.quiz.entity.Quiz;
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

    public void createQuiz(RequestCreateQuizDto requestCreateQuizDto) {
        Chapter foundChapter = chapterRepository.findById(requestCreateQuizDto.chapterId())
                .orElse(null);
        Quiz newQuiz = Quiz.builder()
                .type(requestCreateQuizDto.type())
                .title(requestCreateQuizDto.title())
                .answer(requestCreateQuizDto.answer())
                .chapter(foundChapter)
                .question(requestCreateQuizDto.question())
                .build();
        quizRepository.save(newQuiz);
    }

    public ResponseFindByOneQuizDto findByOne(Long quiz_id) {
        Quiz foundQuiz = quizRepository.findById(quiz_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        return ResponseFindByOneQuizDto.builder()
                .quizId(foundQuiz.getId())
                .chapterId(foundQuiz.getChapter().getId())
                .question(foundQuiz.getQuestion())
                .answer(foundQuiz.getAnswer())
                .title(foundQuiz.getTitle())
                .type(foundQuiz.getType())
                .build();
    }

    public ResponseFindByManyQuizDto findByMany() {
        List<Quiz> quizList = quizRepository.findAll();
        List<ResponseFindByOneQuizDto> responseFindByOneQuizDtoList = new ArrayList<>();
        for (Quiz quiz : quizList) {
            responseFindByOneQuizDtoList.add(
                    ResponseFindByOneQuizDto.builder()
                            .quizId(quiz.getId())
                            .title(quiz.getTitle())
                            .type(quiz.getType())
                            .chapterId(quiz.getChapter().getId())
                            .answer(quiz.getAnswer())
                            .question(quiz.getQuestion())
                            .build()
            );
        }
        return new ResponseFindByManyQuizDto(responseFindByOneQuizDtoList);
    }

    public ResponseFindByManyQuizDto findQuizzesInChapter(Long chapterId) {
        Chapter foundChapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        List<Quiz> quizList = quizRepository.findAll();
        List<ResponseFindByOneQuizDto> responseFindByOneQuizDtoList = new ArrayList<>();
        for (Quiz quiz : quizList) {
            if (quiz.getChapter().getId().equals(foundChapter.getId())) {
                responseFindByOneQuizDtoList.add(
                        ResponseFindByOneQuizDto.builder()
                                .quizId(quiz.getId())
                                .title(quiz.getTitle())
                                .type(quiz.getType())
                                .chapterId(quiz.getChapter().getId())
                                .answer(quiz.getAnswer())
                                .question(quiz.getQuestion())
                                .build()
                );
            }
        }
        return new ResponseFindByManyQuizDto(responseFindByOneQuizDtoList);
    }
}
