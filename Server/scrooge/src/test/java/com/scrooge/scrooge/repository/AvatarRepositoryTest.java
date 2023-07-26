package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.Avatar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AvatarRepositoryTest {

    @Autowired
    AvatarRepository avatarRepository;

    @Test
    public void createAvatar() {
        Avatar avatar = new Avatar();
        avatar.setName("avt1");
        avatar.setImgAddress("C:\\Users\\SSAFY\\Desktop\\S09P12E106\\Server\\scrooge\\src\\main\\resources\\static\\assets\\sample_avatar.png");
        avatarRepository.save(avatar);

        Avatar foundAvatar = avatarRepository.findById(avatar.getId()).orElse(null);
        assertNotNull(foundAvatar);
        assertEquals(avatar.getName(), foundAvatar.getName());
        assertEquals(avatar.getImgAddress(), foundAvatar.getImgAddress());
    }
}
