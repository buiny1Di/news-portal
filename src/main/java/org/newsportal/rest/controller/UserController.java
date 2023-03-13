package org.newsportal.rest.controller;
import org.newsportal.service.UserService;
import org.newsportal.service.impl.UserServiceImpl;
import org.newsportal.service.model.News;
import org.newsportal.service.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news-portal")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllNews() {
        return ResponseEntity.ok(userService.getAll().orElseThrow(RuntimeException::new));

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getNewsById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(userService.getUserById(id).orElseThrow(RuntimeException::new));
    }

}
