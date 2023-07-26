package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.Level;
import com.scrooge.scrooge.domain.User;
import com.scrooge.scrooge.dto.SignUpRequestDto;
import com.scrooge.scrooge.repository.LevelRepository;
import com.scrooge.scrooge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final LevelRepository levelRepository;

    public User signUp(SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setName(signUpRequestDto.getName());
        user.setNickname(signUpRequestDto.getNickname());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(signUpRequestDto.getPassword());

        user.setExp(0);
        user.setStreak(0);
        user.setWeeklyConsum(0);
        user.setWeeklyGoal(0);
        user.setJoinedAt(LocalDateTime.now());

        Level defaultLevel = levelRepository.findById(1L).orElse(null);
        user.setLevel(defaultLevel);

        return userRepository.save(user);
    }
}
