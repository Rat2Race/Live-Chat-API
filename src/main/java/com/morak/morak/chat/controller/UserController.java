package com.morak.morak.chat.controller;

import com.morak.morak.chat.domain.RoleType;
import com.morak.morak.chat.dto.UserDto;
import com.morak.morak.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/save")
    public ResponseEntity<?> saveUser(UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PostMapping("/user/role")
    public ResponseEntity<?> getRole(String username) {
        RoleType roleType = userService.getRoleType(username);
        return ResponseEntity.status(HttpStatus.OK).body(roleType);
    }

}
