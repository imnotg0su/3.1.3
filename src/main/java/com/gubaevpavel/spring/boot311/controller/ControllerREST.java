package com.gubaevpavel.spring.boot311.controller;

import com.gubaevpavel.spring.boot311.exception_handing.NoSuchUserException;
import com.gubaevpavel.spring.boot311.model.Role;
import com.gubaevpavel.spring.boot311.model.User;
import com.gubaevpavel.spring.boot311.service.RoleService;
import com.gubaevpavel.spring.boot311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerREST {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public ControllerREST(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers () {
        List<User> users = userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUserById(@PathVariable int id) {
        User user = userService.userById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with ID = " + id + "in Database.");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser (@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.allRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}

