package com.rat.race.chat.dto;

import com.rat.race.chat.domain.RoleType;

public record UserDto(
        String username,
        RoleType role
) {
}
