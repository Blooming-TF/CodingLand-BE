package com.codingland.apiservice.quiz.presentation;

import com.codingland.domain.quiz.dto.ResponseDifficultyListDto;
import com.codingland.domain.quiz.service.DifficultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/difficulty")
@RequiredArgsConstructor
public class DifficultyController {
    private final DifficultyService difficultyService;

    @PostMapping
    public ResponseEntity<Void> createDifficulty(@RequestParam int level) {
        difficultyService.createDifficulty(level);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDifficultyListDto> getDifficultyList() {
        ResponseDifficultyListDto result = difficultyService.getDifficultyList();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping
    public ResponseEntity<Void> editDifficulty(@RequestParam Long difficulty_id, @RequestParam int level) {
        difficultyService.editDifficulty(difficulty_id, level);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDifficulty(@RequestParam Long difficulty_id) {
        difficultyService.deleteDifficulty(difficulty_id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }



}
