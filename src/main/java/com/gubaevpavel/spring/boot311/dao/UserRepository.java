package com.gubaevpavel.spring.boot311.dao;



import com.gubaevpavel.spring.boot311.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
