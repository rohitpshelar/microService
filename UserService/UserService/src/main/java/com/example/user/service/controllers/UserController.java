package com.example.user.service.controllers;

import com.example.user.service.entities.User;
import com.example.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        var user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<User> getUser(@PathVariable  String uid){
        return ResponseEntity.ok(userService.getUser(uid));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        var user1 = userService.getAllUser();
        return ResponseEntity.ok().body(user1);
    }

}
