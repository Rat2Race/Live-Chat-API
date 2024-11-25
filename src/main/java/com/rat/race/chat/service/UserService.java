package com.rat.race.chat.service;

import com.rat.race.chat.domain.RoleType;
import com.rat.race.chat.domain.User;
import com.rat.race.chat.dto.UserDto;
import com.rat.race.chat.repository.UserRepository;
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
