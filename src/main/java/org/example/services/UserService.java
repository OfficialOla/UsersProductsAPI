package org.example.services;

import org.example.dtos.*;
import org.example.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    UserResponse getUserById(int id) throws UserNotFoundException;

    List<UserResponse> getAllUsers(int page, int items);

    ApiResponse<?> deleteUserById(int id);

    void deleteAll();

    UserResponse updateUserDetails(int id, UpdateUserRequest updateUserRequest);


}