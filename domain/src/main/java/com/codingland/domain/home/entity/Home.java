package com.codingland.domain.home.entity;

import com.codingland.domain.character.entity.Character;
import com.codingland.domain.home.dto.RequestEditHomeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 홈 ID

    @OneToOne
    @JoinColumn(name = "CHARACTER_ID")
    private Character character;

    public void editHome(RequestEditHomeDto requestEditHomeDto) {
        this.id = requestEditHomeDto.id();
    }
}
