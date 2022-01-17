package com.gubaevpavel.spring.boot311.service;


import com.gubaevpavel.spring.boot311.repository.UserRepository;
import com.gubaevpavel.spring.boot311.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional (readOnly = true)
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

//    @Override
//    public void edit(User user) {
//        if (!user.getPassword().equals(getUserByName(user.getUsername()).getPassword())) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
//        userDAO.edit(user);
//    }

    @Override
    @Transactional (readOnly = true)
    public User userById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional (readOnly = true)
    public User getUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }
}
