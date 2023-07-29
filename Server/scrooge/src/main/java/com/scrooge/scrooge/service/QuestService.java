package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.Quest;
import com.scrooge.scrooge.dto.QuestDto;
import com.scrooge.scrooge.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepository questRepository;

    public Optional<QuestDto> getQuest(Long questId) {
        return questRepository.findById(questId).map(quest -> {
            QuestDto questDto = new QuestDto();
            questDto.setId(quest.getId());
            questDto.setTitle(quest.getTitle());
            questDto.setDescription(quest.getDescription());
            questDto.setMaxCount(quest.getMaxCount());

            return questDto;
        });
    }

    public List<QuestDto> getAllQuests() {
        List<Quest> quests = questRepository.findAll();
        return quests.stream()
                .map(QuestDto::new)
                .collect(Collectors.toList());
    }
}
