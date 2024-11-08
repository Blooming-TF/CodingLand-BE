package com.codingland.apiservice.chapter.presentation;

import com.codingland.domain.chapter.dto.ResponseIsChapterClearedDto;
import com.codingland.domain.chapter.dto.ResponseIsChapterClearedListDto;
import com.codingland.domain.chapter.service.IsChapterClearedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/isChapterCleared")
@RequiredArgsConstructor
@Tag(name = "[IsChapterCleared] 챕터 완료 여부 API", description = "챕터 완료 처리, 챕터 완료 여부 조회, 챕터 완료 여부 수정, 챕터 완료 여부 삭제")
public class IsChapterClearedController {
    private final IsChapterClearedService isChapterClearedService;

    @PostMapping
    @Operation(summary = "챕터 완료 여부 등록", description = """
            (사용자) 풀이 완료 된 챕터를 완료처리 합니다.
            """)
    public ResponseEntity<Void> solvedChapter(@RequestParam Long chapterId,
                                              @RequestParam Long userId) {
        isChapterClearedService.clearedChapter(chapterId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("{isChapterCleared_id}")
    @Operation(summary = "챕터 완료 여부 단 건 조회", description = """
            (관리자) 챕터 완료 여부 단 건 조회
            """)
    public ResponseEntity<ResponseIsChapterClearedDto> getIsChapterCleared(@PathVariable Long isChapterCleared_id) {
        ResponseIsChapterClearedDto result = isChapterClearedService.getIsChapterCleared(isChapterCleared_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/all")
    @Operation(summary = "등록된 챕터 완료 여부 모두 조회", description = """
            (관리자) 데이터베이스에 등록된 챕터 완료 여부를 모두 조회
            """)
    public ResponseEntity<ResponseIsChapterClearedListDto> getAllIsChapterClearedList() {
        ResponseIsChapterClearedListDto result = isChapterClearedService.getAllIsChapterClearedList();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping
    @Operation(summary = "챕터 완료 여부 수정", description = """
            (관리자) 챕터 완료 여부를 수정합니다.
            """)
    public ResponseEntity<Void> editIsChapterCleared(@RequestParam boolean isCleared,
                                                     @RequestParam Long isChapterCleared_id) {
        isChapterClearedService.editIsChapterCleared(isChapterCleared_id, isCleared);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
