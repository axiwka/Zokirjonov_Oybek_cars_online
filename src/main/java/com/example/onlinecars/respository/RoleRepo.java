package com.example.onlinecars.respository;

import com.example.onlinecars.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findRoleByRoleName(String roleName);
}
