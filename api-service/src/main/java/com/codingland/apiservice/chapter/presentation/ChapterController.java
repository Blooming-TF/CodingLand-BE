package com.codingland.apiservice.chapter.presentation;

import com.codingland.domain.chapter.dto.RequestChapterDto;
import com.codingland.domain.chapter.dto.RequestEditChapterDto;
import com.codingland.domain.chapter.dto.ResponseChapterDto;
import com.codingland.domain.chapter.dto.ResponseChapterListDto;
import com.codingland.domain.chapter.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chapter")
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterService chapterService;

    @PostMapping
    public ResponseEntity<Void> createChapter(@RequestBody RequestChapterDto requestChapterDto) {
        chapterService.createChapter(requestChapterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{chapter_id}")
    public ResponseEntity<ResponseChapterDto> getChapter(@PathVariable Long chapter_id) {
        ResponseChapterDto result = chapterService.getChapter(chapter_id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseChapterListDto> getAllChapters() {
        ResponseChapterListDto result = chapterService.getChapterList();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/{chapter_id}")
    public ResponseEntity<Void> editChapter(@PathVariable Long chapter_id,
                                            @RequestBody RequestEditChapterDto requestChapterDto) {
        chapterService.editChapter(chapter_id, requestChapterDto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/{chapter_id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long chapter_id) {
        chapterService.deleteChapter(chapter_id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
