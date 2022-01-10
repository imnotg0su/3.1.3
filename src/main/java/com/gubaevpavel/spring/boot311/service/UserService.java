package com.gubaevpavel.spring.boot311.service;

import com.gubaevpavel.spring.boot311.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    void saveUser (User user);
    void delete (int id);
//    void edit (User user);
    User userById (int id);
    User getUserByName(String username);
}
