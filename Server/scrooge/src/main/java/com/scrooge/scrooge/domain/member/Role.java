package com.scrooge.scrooge.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("USER", "일반사용자");

    private final String key;
    private final String title;
}
