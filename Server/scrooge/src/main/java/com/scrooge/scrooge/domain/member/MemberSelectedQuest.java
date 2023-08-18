package com.scrooge.scrooge.domain.member;

import com.scrooge.scrooge.domain.Quest;
import com.scrooge.scrooge.domain.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class MemberSelectedQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
    // 직렬화 안되고 에러뜨던거 EAGER로 바꾸니까 해결됐음
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @Column(name = "complete_count")
    private int completeCount = 0;

    @Column(name = "is_selected")
    private boolean isSelected = false;

    @Column(name = "is_done")
    private boolean isDone = false;
}
