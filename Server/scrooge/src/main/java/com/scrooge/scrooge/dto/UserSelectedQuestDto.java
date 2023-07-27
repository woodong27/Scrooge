package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.Quest;
import com.scrooge.scrooge.domain.User;
import com.scrooge.scrooge.domain.UserSelectedQuest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSelectedQuestDto {

    private Long id;
    private User user;
    private Quest quest;
    private int completeCount;

    @Builder
    private UserSelectedQuestDto(UserSelectedQuest userSelectedQuest) {
        this.id = userSelectedQuest.getId();
        this.user = userSelectedQuest.getUser();
        this.quest = userSelectedQuest.getQuest();
        this.completeCount = userSelectedQuest.getCompleteCount();
    }
}
