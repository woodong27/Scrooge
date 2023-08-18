package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.Quest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestDto {
    private Long id;
    private String title;
    private String description;
    private int maxCount;

    @Builder
    public QuestDto(Quest quest) {
        this.id = quest.getId();
        this.title = quest.getTitle();
        this.description = quest.getDescription();
        this.maxCount = quest.getMaxCount();
    }
}
