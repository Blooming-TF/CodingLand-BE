package com.codingland.apiservice.quiz.presentation;

import com.codingland.domain.quiz.dto.*;
import com.codingland.domain.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<Void> createQuiz(@RequestBody RequestCreateQuizDto requestCreateQuizDto) {
        quizService.createQuiz(requestCreateQuizDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{quiz_id}")
    public ResponseEntity<ResponseQuizDto> getQuiz(@PathVariable Long quiz_id, @RequestParam Long user_id) {
        ResponseQuizDto result = quizService.findByOne(quiz_id, user_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseQuizListDto> getAllQuizzes() {
        ResponseQuizListDto result = quizService.findByMany();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping
    public ResponseEntity<Void> editQuiz(@RequestBody RequestEditQuizDto requestEditQuizDto) {
        quizService.editQuiz(requestEditQuizDto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/{quiz_id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long quiz_id) {
        quizService.deleteQuiz(quiz_id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
