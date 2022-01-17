package com.gubaevpavel.spring.boot311.repository;



import com.gubaevpavel.spring.boot311.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
