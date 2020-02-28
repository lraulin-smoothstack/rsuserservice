package com.smoothstack.december.rsuserservice.controller;

import com.smoothstack.december.rsuserservice.model.User;
import com.smoothstack.december.rsuserservice.model.UserDto;
import com.smoothstack.december.rsuserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private static final int ADMIN = 4;
    private static final int ACCOUNTANT = 3;
    private static final int CLERK = 2;
    private static final int CUSTOMER = 1;

    @GetMapping()
    public ResponseEntity<List<User>> getUser() throws Exception {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable("email") String email) throws Exception {
        User user = userService.getUser(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createCustomer(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.createUser(user, CUSTOMER));
    }

    @PostMapping("/register/clerk")
    public ResponseEntity<User> createClerk(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.createUser(user, CLERK));
    }

    @PostMapping("/register/accountant")
    public ResponseEntity<User> createAccountant(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.createUser(user, ACCOUNTANT));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<User> createAdmin(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.createUser(user, ADMIN));
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<User> deactivateUser(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.deleteUser(email));
    }
}