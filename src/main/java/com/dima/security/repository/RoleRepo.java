package com.dima.security.repository;

import com.dima.security.domain.Role;
import com.dima.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
