package com.dima.security.api;

import com.dima.security.domain.User;
import com.dima.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public ResponseEntity <List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }
}
