package com.gubaevpavel.spring.boot311.repository;

import com.gubaevpavel.spring.boot311.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String name);
}
