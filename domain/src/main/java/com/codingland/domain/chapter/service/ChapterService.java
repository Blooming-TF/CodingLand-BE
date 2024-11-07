package com.codingland.domain.chapter.service;

import com.codingland.domain.chapter.dto.RequestChapterDto;
import com.codingland.domain.chapter.dto.RequestEditChapterDto;
import com.codingland.domain.chapter.dto.ResponseChapterDto;
import com.codingland.domain.chapter.dto.ResponseChapterListDto;
import com.codingland.domain.chapter.entity.Chapter;
import com.codingland.domain.chapter.repository.ChapterRepository;
import com.codingland.domain.quiz.dto.ResponseFindByChapter;
import com.codingland.domain.quiz.entity.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;

    /**
     * 챕터를 등록하는 메서드입니다.
     * @author 김원정
     * @param requestChapterDto 챕터 등록 Dto
     */
    public void createChapter(RequestChapterDto requestChapterDto) {
        Chapter newChapter = new Chapter(requestChapterDto.name());
        chapterRepository.save(newChapter);
    }

    /**
     * 챕터를 단 건 조회하는 메서드입니다.
     * @author 김원정
     * @param chapter_id 챕터의 id
     * @throws RuntimeException
     */
    public ResponseChapterDto getChapter(Long chapter_id) {
        Chapter foundChapter = chapterRepository.findById(chapter_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        List<ResponseFindByChapter> responseQuizDtoList = new ArrayList<>();
        for (Quiz quiz : foundChapter.getQuizzes()) {
            responseQuizDtoList.add(
              ResponseFindByChapter.builder()
                      .quizId(quiz.getId())
                      .isCleared(quiz.getIsQuizcleared() != null && quiz.getIsQuizcleared().isCleared())
                      .level(quiz.getDifficulty().getLevel())
                      .title(quiz.getTitle())
                      .type(quiz.getType())
                      .build()
            );
        }
        return new ResponseChapterDto(
                foundChapter.getId(),
                foundChapter.getName(),
                responseQuizDtoList
        );
    }

    /**
     * 데이터베이스에 등록된 챕터를 모두 조회하는 메서드입니다.
     * @author 김원정
     * @throws RuntimeException
     */
    public ResponseChapterListDto getChapterList() {
        List<Chapter> foundChapterList = chapterRepository.findAll();
        List<ResponseChapterDto> responseChapterDtoList = new ArrayList<>();
        for (Chapter chapter : foundChapterList) {
            List<ResponseFindByChapter> responseFindByChapterList = new ArrayList<>();
            if (!chapter.getQuizzes().isEmpty()) {
                for (Quiz quiz : chapter.getQuizzes()) {
                    responseFindByChapterList.add(
                            ResponseFindByChapter.builder()
                                    .quizId(quiz.getId())
                                    .isCleared(quiz.getIsQuizcleared().isCleared())
                                    .level(quiz.getDifficulty().getLevel())
                                    .title(quiz.getTitle())
                                    .type(quiz.getType())
                                    .build()
                    );
                }
            }
            responseChapterDtoList.add(
                    new ResponseChapterDto(
                            chapter.getId(),
                            chapter.getName(),
                            responseFindByChapterList
                    )
            );
        }
        return new ResponseChapterListDto(responseChapterDtoList);
    }

    /**
     * 챕터를 수정하는 메서드입니다.
     * @author 김원정
     * @param chapter_id 챕터의 id
     * @throws RuntimeException
     */
    public void editChapter(Long chapter_id, RequestEditChapterDto requestChapterDto) {
        Chapter foundChapter = chapterRepository.findById(chapter_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        foundChapter.editChapter(requestChapterDto);
        chapterRepository.save(foundChapter);
    }

    /**
     * 챕터 삭제하는 메서드입니다.
     * @author 김원정
     * @param chapter_id 챕터의 id
     * @throws RuntimeException
     */
    public void deleteChapter(Long chapter_id) {
        Chapter foundChapter = chapterRepository.findById(chapter_id).orElseThrow(() -> new RuntimeException("임시 Exception"));
        chapterRepository.delete(foundChapter);
    }
}
