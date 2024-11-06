package com.codingland.domain.chapter.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IsChapterCleared {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isCleared;

    @ManyToOne
    @JoinColumn(name = "CHAPTER_ID")
    private Chapter chapter;
}
