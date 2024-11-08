package com.codingland.apiservice.chapter.presentation;

import com.codingland.domain.chapter.dto.RequestChapterDto;
import com.codingland.domain.chapter.dto.RequestEditChapterDto;
import com.codingland.domain.chapter.dto.ResponseChapterDto;
import com.codingland.domain.chapter.dto.ResponseChapterListDto;
import com.codingland.domain.chapter.service.ChapterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chapter")
@RequiredArgsConstructor
@Tag(name = "[Chapter] 챕터 API", description = "챕터 생성, 챕터 조회, 챕터 이름 수정, 챕터 삭제")
public class ChapterController {
    private final ChapterService chapterService;

    @PostMapping
    @Operation(summary = "챕터 등록", description = """
            (관리자) 챕터를 등록합니다.
            """)
    public ResponseEntity<Void> createChapter(@RequestBody RequestChapterDto requestChapterDto) {
        chapterService.createChapter(requestChapterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{chapter_id}")
    @Operation(summary = "챕터 단 건 조회", description = """
            (사용자) 챕터를 단 건 조회합니다.
            """)
    public ResponseEntity<ResponseChapterDto> getChapter(@PathVariable Long chapter_id, @RequestParam Long user_id) {
        ResponseChapterDto result = chapterService.getChapter(chapter_id, user_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/all")
    @Operation(summary = "등록된 챕터 모두 조회", description = """
            (관리자) 데이터베이스에 등록된 챕터를 모두 조회합니다.
            """)
    public ResponseEntity<ResponseChapterListDto> getAllChapters() {
        ResponseChapterListDto result = chapterService.getChapterList();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/{chapter_id}")
    @Operation(summary = "챕터 수정", description = """
            (관리자) 챕터 이름을 수정합니다.
            """)
    public ResponseEntity<Void> editChapter(@PathVariable Long chapter_id,
                                            @RequestBody RequestEditChapterDto requestChapterDto) {
        chapterService.editChapter(chapter_id, requestChapterDto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/{chapter_id}")
    @Operation(summary = "챕터 삭제", description = """
            (관리자) 챕터를 삭제합니다. 하위 Quiz를 삭제합니다.
            """)
    public ResponseEntity<Void> deleteChapter(@PathVariable Long chapter_id) {
        chapterService.deleteChapter(chapter_id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
