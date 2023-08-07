package com.scrooge.scrooge.dto.member;

import com.scrooge.scrooge.domain.member.MemberSelectedQuest;
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
    private boolean isSelected;
    private boolean isDone;

    @Builder
    public MemberSelectedQuestDto(MemberSelectedQuest userSelectedQuest) {
        this.id = userSelectedQuest.getId();
//        this.user = userSelectedQuest.getUser();
        this.quest = userSelectedQuest.getQuest();
        this.completeCount = userSelectedQuest.getCompleteCount();
        this.isSelected = userSelectedQuest.isSelected();
        this.isDone = userSelectedQuest.isDone();
    }
}
