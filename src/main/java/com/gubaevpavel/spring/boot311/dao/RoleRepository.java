package com.gubaevpavel.spring.boot311.dao;

import com.gubaevpavel.spring.boot311.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String name);
}
