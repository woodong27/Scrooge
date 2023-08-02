package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.dto.AvatarDto;
import com.scrooge.scrooge.service.AvatarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Avatar APIs", description = "APIs about Badge")
@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "Get avatars", description = "Get all avatars")
    @GetMapping("")
    public ResponseEntity<List<AvatarDto>> getAvatars() {
        List<AvatarDto> avatarDtos = avatarService.getAvatars();
        return ResponseEntity.ok(avatarDtos);
    }

    @Operation(summary = "Get avatar", description = "Get one avatar")
    @GetMapping("/{avatarId}")
    public ResponseEntity<AvatarDto> getAvatar (@PathVariable("avatarId") Long avatarId) {
        AvatarDto avatarDto = avatarService.getAvatarById(avatarId);
        return ResponseEntity.ok(avatarDto);
    }

    @Operation(summary = "Select main avatar", description = "Select member's main avatar")
    @PutMapping("/{avatarId}")
    public ResponseEntity<?> selectMainAvatar(@RequestHeader("Authorization")String header, @PathVariable("avatarId")Long avatarId) {
        String token = jwtTokenProvider.extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("그런 유저 없습니다.");
        }

        AvatarDto avatarDto = avatarService.selectMainAvatar(avatarId, jwtTokenProvider.extractMemberId(token));
        return ResponseEntity.ok(avatarDto);
    }
}
