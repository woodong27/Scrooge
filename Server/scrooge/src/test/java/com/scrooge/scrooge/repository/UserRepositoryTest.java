package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.Avatar;
import com.scrooge.scrooge.domain.Level;
import com.scrooge.scrooge.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    AvatarRepository avatarRepository;

    @Test
    public void createUser() {
        User user = new User();
        user.setName("a");
        user.setNickname("a");
        user.setEmail("test@test.com");
        user.setPassword("test");
        user.setExp(10);
        user.setStreak(1);
        user.setWeekly_goal(5);
        user.setWeekly_consum(2);

        Level level = new Level();
        level.setLevel(1);
        level.setRequired_exp(100);
        level.setGacha(1);
        level = levelRepository.save(level);
        user.setLevel(level);

        Avatar avatar = new Avatar();
        avatar.setName("test1");
        avatar.setImg_address("C:\\Users\\SSAFY\\Desktop\\S09P12E106\\Server\\scrooge\\src\\main\\resources\\static\\assets\\sample_avatar.png");
        avatar = avatarRepository.save(avatar);
        user.setMain_avatar(avatar);

        userRepository.save(user);

        User foundUser = userRepository.findById(user.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(user.getName(), foundUser.getName());
        assertEquals(user.getNickname(), foundUser.getNickname());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getPassword(), foundUser.getPassword());
        assertEquals(user.getExp(), foundUser.getExp());
        assertEquals(user.getStreak(), foundUser.getStreak());
        assertEquals(user.getWeekly_goal(), foundUser.getWeekly_goal());
        assertEquals(user.getWeekly_consum(), foundUser.getWeekly_consum());
    }
}
