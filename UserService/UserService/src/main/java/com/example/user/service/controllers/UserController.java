package com.example.user.service.controllers;

import com.example.user.service.entities.User;
import com.example.user.service.services.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        var user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{uid}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelRetryFallback")
    @RateLimiter(name = "ratingHotelRateLimiter", fallbackMethod = "ratingHotelRateLimiterFallback")
    public ResponseEntity<User> getUser(@PathVariable String uid){
        logger.info("getUser is executed for Udrt Id : {}",uid);
        return ResponseEntity.ok(userService.getUser(uid));
    }

    public ResponseEntity<User> ratingHotelFallback(String uid, Exception ex){
        logger.info("Circuit Fallback is executed because service is down : {}",ex.getMessage());
        var user = User.builder().email("dummy").name("dummy").about("Created dummy as some service is down").uid("123").build();
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingHotelRetryFallback(String uid, Exception ex){
        logger.info("Retry Fallback is executed because service is down : {}",ex.getMessage());
        var user = User.builder().email("dummy").name("dummy").about("Created dummy as some service is down").uid("123").build();
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingHotelRateLimiterFallback(String uid, Exception ex){
        logger.info("Rate Limiter Fallback is executed because request is above set limit : {}",ex.getMessage());
        var user = User.builder().email("dummy").name("dummy").about("Created dummy as some service is down").uid("123").build();
        return ResponseEntity.badRequest().body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        var user1 = userService.getAllUser();
        return ResponseEntity.ok().body(user1);
    }

}
