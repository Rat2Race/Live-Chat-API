package com.morak.morak.chat.repository;

import com.morak.morak.chat.domain.RoleType;
import com.morak.morak.chat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    RoleType findByUsername(String username);
}
