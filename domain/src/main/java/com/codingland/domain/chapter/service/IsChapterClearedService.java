package com.codingland.domain.chapter.service;

import com.codingland.domain.chapter.dto.ResponseIsChapterClearedDto;
import com.codingland.domain.chapter.dto.ResponseIsChapterClearedListDto;
import com.codingland.domain.chapter.entity.Chapter;
import com.codingland.domain.chapter.entity.IsChapterCleared;
import com.codingland.domain.chapter.repository.ChapterRepository;
import com.codingland.domain.chapter.repository.IsChapterClearedRepository;
import com.codingland.domain.user.entity.User;
import com.codingland.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IsChapterClearedService {
    private final IsChapterClearedRepository isChapterClearedRepository;
    private final UserRepository userRepository;
    private final ChapterRepository chapterRepository;

    /**
     * 풀이 완료 된 챕터를 완료처리 하는 메서드.
     * @author 김원정
     * @param chapter_id 챕터의 id
     * @param user_id 유저의 id
     * @throws RuntimeException
     */
    public void clearedChapter(Long chapter_id, Long user_id) {
        User foundUser = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        Chapter foundChapter = chapterRepository.findById(chapter_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        IsChapterCleared newIsChapterCleared = IsChapterCleared.thisChapterIsCleared(foundChapter, foundUser);
        isChapterClearedRepository.save(newIsChapterCleared);
    }

    /**
     * 챕터 완료 여부를 단 건 조회하는 메서드입니다.
     * @author 김원정
     * @param isChapterCleared_id 챕터 완료 여부의 id
     * @throws RuntimeException
     */
    public ResponseIsChapterClearedDto getIsChapterCleared(Long isChapterCleared_id) {
        IsChapterCleared foundIsChapterCleared = isChapterClearedRepository.findById(isChapterCleared_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        return ResponseIsChapterClearedDto.builder()
                .id(foundIsChapterCleared.getId())
                .ChapterId(foundIsChapterCleared.getChapter().getId())
                .userId(foundIsChapterCleared.getUser().getUserId())
                .isCleared(foundIsChapterCleared.isCleared())
                .build();
    }

    /**
     * 데이터베이스에 등록된 챕터 완료 여부를 모두 조회하는 메서드입니다.
     * @author 김원정
     */
    public ResponseIsChapterClearedListDto getAllIsChapterClearedList() {
        List<IsChapterCleared> isChapterClearedList = isChapterClearedRepository.findAll();
        List<ResponseIsChapterClearedDto> responseIsChapterClearedListDtoList = new ArrayList<>();
        for (IsChapterCleared isChapterCleared : isChapterClearedList) {
            responseIsChapterClearedListDtoList.add(
                    ResponseIsChapterClearedDto.builder()
                            .id(isChapterCleared.getId())
                            .ChapterId(isChapterCleared.getChapter().getId())
                            .userId(isChapterCleared.getUser().getUserId())
                            .isCleared(isChapterCleared.isCleared())
                            .build()
            );
        }
        return new ResponseIsChapterClearedListDto(responseIsChapterClearedListDtoList);
    }

    /**
     * 챕터 완료 여부를 수정하는 메서드입니다.
     * @author 김원정
     * @param isChapterCleared_id 챕터 완료 여부의 id
     * @param isCleared 완료 여부
     * @throws RuntimeException
     */
    @Transactional
    public void editIsChapterCleared(Long isChapterCleared_id, boolean isCleared) {
        IsChapterCleared foundIsChapterCleared = isChapterClearedRepository.findById(isChapterCleared_id)
                .orElseThrow(() -> new RuntimeException("임시 Exception"));
        foundIsChapterCleared.changeIsCleared(isCleared);
    }
}
