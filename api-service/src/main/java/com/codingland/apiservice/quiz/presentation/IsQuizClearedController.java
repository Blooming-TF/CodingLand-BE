package com.codingland.apiservice.quiz.presentation;

import com.codingland.domain.quiz.dto.ResponseIsQuizClearedDto;
import com.codingland.domain.quiz.dto.ResponseIsQuizClearedListDto;
import com.codingland.domain.quiz.service.IsQuizClearedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/isQuizCleared")
@RequiredArgsConstructor
@Tag(name = "[IsQuizCleared] 문제 완료 여부 API", description = "문제 완료 처리, 문제 완료 여부 조회, 문제 완료 여부 수정, 문제 완료 여부 삭제")
public class IsQuizClearedController {
    private final IsQuizClearedService isQuizClearedService;

    @PostMapping
    @Operation(summary = "퀴즈 완료 여부 등록", description = """
            (사용자) 풀이가 완료된 퀴즈를 등록처리 합니다.
            """)
    public ResponseEntity<Void> solvedProblem(@RequestParam Long quiz_id, @RequestParam Long user_id) {
        isQuizClearedService.solveProblem(quiz_id, user_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{isQuizCleared_id}")
    @Operation(summary = "퀴즈 완료 여부 단 건 조회", description = """
            (관리자) 퀴즈 완료 여부를 단건 조회 합니다.
            """)
    public ResponseEntity<ResponseIsQuizClearedDto> getIsQuizCleared(@PathVariable Long isQuizCleared_id) {
        ResponseIsQuizClearedDto result = isQuizClearedService.getIsQuizCleared(isQuizCleared_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/all")
    @Operation(summary = "등록된 퀴즈 완료 여부 모두 조회", description = """
            (관리자) 데이터베이스에 등록된 퀴즈 완료 여부를 모두 조회합니다.
            """)
    public ResponseEntity<ResponseIsQuizClearedListDto> getAllIsQuizClearedList() {
        ResponseIsQuizClearedListDto result = isQuizClearedService.getAllIsQuizCleared();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/{isQuizCleared_id}")
    @Operation(summary = "퀴즈 완료 여부 수정", description = """
            (관리자) 퀴즈 완료 여부를 수정합니다.
            """)
    public ResponseEntity<Void> editIsQuizCleared(@PathVariable Long isQuizCleared_id,
                                                  @RequestParam Long quiz_id,
                                                  @RequestParam Long user_id,
                                                  @RequestParam boolean isCleared) {
        isQuizClearedService.editIsQuizCleared(isQuizCleared_id, quiz_id, user_id, isCleared);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
