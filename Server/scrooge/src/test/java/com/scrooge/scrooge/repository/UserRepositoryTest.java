package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.Avatar;
import com.scrooge.scrooge.domain.Level;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    AvatarRepository avatarRepository;

    @Test
    public void createUser() {
        Member user = new Member();
        user.setName("a");
        user.setNickname("a");
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setExp(10);
        user.setStreak(1);
        user.setWeeklyGoal(5);
        user.setWeeklyConsum(2);

        Level level = new Level();
        level.setLevel(1);
        level.setRequiredExp(100);
        level.setGacha(1);
        level = levelRepository.save(level);
        user.setLevel(level);

        Avatar avatar = new Avatar();
        avatar.setName("test1");
        avatar.setImgAddress("/static/assets/sample_avatar.png");
        avatar = avatarRepository.save(avatar);
        user.setMainAvatar(avatar);

        memberRepository.save(user);

        Member foundUser = memberRepository.findById(user.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(user.getName(), foundUser.getName());
        assertEquals(user.getNickname(), foundUser.getNickname());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getPassword(), foundUser.getPassword());
        assertEquals(user.getExp(), foundUser.getExp());
        assertEquals(user.getStreak(), foundUser.getStreak());
        assertEquals(user.getWeeklyGoal(), foundUser.getWeeklyGoal());
        assertEquals(user.getWeeklyConsum(), foundUser.getWeeklyConsum());
    }
}
