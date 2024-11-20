package com.codingland.domain.character.entity;

import com.codingland.domain.character.common.CharacterTypeEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Character {

    @Id
    @Column(name = "CHARACTER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int level;
    private CharacterTypeEnum type;
    private int activityPoints;

}
