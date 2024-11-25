package com.rat.race.chat.repository;

import com.rat.race.chat.domain.RoleType;
import com.rat.race.chat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    RoleType findByUsername(String username);
}
