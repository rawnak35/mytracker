package com.finTrack.mytracker.controller;

import com.finTrack.mytracker.dto.UserDto;
import com.finTrack.mytracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// "services" - api requests
@Controller
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable("username") String username) {
        UserDto savedUser = userService.getUserByUsername(username);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
        UserDto savedUser = userService.getUserById(id);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto updatedUser) {
        UserDto userDto = userService.updateUser(username, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User was deleted successfully");
    }

}
