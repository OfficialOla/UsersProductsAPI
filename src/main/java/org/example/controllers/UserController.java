package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.dtos.CreateUserRequest;
import org.example.dtos.UpdateUserRequest;
import org.example.dtos.UserResponse;
import org.example.exceptions.UserNotFoundException;
import org.example.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user")
@CrossOrigin("*")
@AllArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest createUserRequest){
        var response = userService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id) throws UserNotFoundException {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<?> getAllUsers(@PathVariable int page, @PathVariable int size ){
        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserDetails(@PathVariable int id, @ModelAttribute UpdateUserRequest updateUserRequest){
        var response = userService.updateUserDetails(id, updateUserRequest);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        var response = userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/all/users")
    public void deleteAllUsers(){
        userService.deleteAll();
    }

}
