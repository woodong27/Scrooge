package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.MemberSelectedQuest;
import com.scrooge.scrooge.domain.Quest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSelectedQuestDto {

    private Long id;
    private Quest quest;
    private int completeCount;

    @Builder
    public MemberSelectedQuestDto(MemberSelectedQuest userSelectedQuest) {
        this.id = userSelectedQuest.getId();
//        this.user = userSelectedQuest.getUser();
        this.quest = userSelectedQuest.getQuest();
        this.completeCount = userSelectedQuest.getCompleteCount();
    }
}
