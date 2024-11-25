package com.morak.morak.chat.dto;

import com.morak.morak.chat.domain.RoleType;

public record UserDto(
        String username,
        RoleType role
) {
}
