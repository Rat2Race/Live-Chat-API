package com.morak.morak.chat.service;

import com.morak.morak.chat.domain.RoleType;
import com.morak.morak.chat.domain.User;
import com.morak.morak.chat.dto.UserDto;
import com.morak.morak.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.username())
                .role(userDto.role())
                .build();

        userRepository.save(user);
    }

    public RoleType getRoleType(String username) {
        return userRepository.findByUsername(username);
    }
}
