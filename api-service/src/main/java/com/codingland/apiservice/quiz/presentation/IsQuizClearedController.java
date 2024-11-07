package com.codingland.apiservice.quiz.presentation;

import com.codingland.domain.quiz.dto.ResponseIsQuizClearedDto;
import com.codingland.domain.quiz.dto.ResponseIsQuizClearedListDto;
import com.codingland.domain.quiz.service.IsQuizClearedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/isQuizCleared")
@RequiredArgsConstructor
public class IsQuizClearedController {
    private final IsQuizClearedService isQuizClearedService;

    @PostMapping
    public ResponseEntity<Void> solvedProblem(@RequestParam Long quiz_id, @RequestParam Long user_id) {
        isQuizClearedService.solveProblem(quiz_id, user_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{isQuizCleared_id}")
    public ResponseEntity<ResponseIsQuizClearedDto> getIsQuizCleared(@PathVariable Long isQuizCleared_id) {
        ResponseIsQuizClearedDto result = isQuizClearedService.getIsQuizCleared(isQuizCleared_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseIsQuizClearedListDto> getAllIsQuizClearedList() {
        ResponseIsQuizClearedListDto result = isQuizClearedService.getAllIsQuizCleared();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/{isQuizCleared_id}")
    public ResponseEntity<Void> editIsQuizCleared(@PathVariable Long isQuizCleared_id,
                                                  @RequestParam Long quiz_id,
                                                  @RequestParam Long user_id,
                                                  @RequestParam boolean isCleared) {
        isQuizClearedService.editIsQuizCleared(isQuizCleared_id, quiz_id, user_id, isCleared);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
