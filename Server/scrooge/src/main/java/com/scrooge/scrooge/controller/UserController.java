package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.domain.User;
import com.scrooge.scrooge.dto.SignUpRequestDto;
import com.scrooge.scrooge.dto.UserDto;
import com.scrooge.scrooge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        User user = userService.signUp(signUpRequestDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable("userId") Long userId) {
        Optional<UserDto> userDto = userService.getUserInfo(userId);
        return userDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
