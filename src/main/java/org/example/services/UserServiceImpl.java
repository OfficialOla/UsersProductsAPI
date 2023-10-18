package org.example.services;

import lombok.AllArgsConstructor;
import org.example.dtos.*;
import org.example.exceptions.ResourceNotFoundException;
import org.example.exceptions.UserNotFoundException;
import org.example.models.User;
import org.example.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.example.utils.AppUtils.buildPageRequest;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        if (userEmailExist(createUserRequest.getEmail()))
            throw new IllegalArgumentException(createUserRequest.getEmail() + "already exist");
        User user = modelMapper.map(createUserRequest, User.class);
        User savedUser =  userRepository.save(user);
        return buildUserRegistrationResponse(savedUser.getId());
    }

    private CreateUserResponse buildUserRegistrationResponse(Long id) {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setMessage("User successfully saved");
        createUserResponse.setId(id);
        return createUserResponse;
    }

    private boolean userEmailExist(String email) {
        Optional<User>foundUser = userRepository.findByEmail(email);
        return foundUser.isPresent();
    }

    @Override
    public UserResponse getUserById(int id) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findById(id);
        User thisUser = foundUser.orElseThrow(()-> new UserNotFoundException(
                String.format("User with %s not found", id)));
        return buildGetUserResponse(thisUser);
    }

    private static UserResponse buildGetUserResponse(User thisUser) {
        return UserResponse.builder()
                .firstName(thisUser.getFirstName())
                .lastName(thisUser.getLastName())
                .email(thisUser.getEmail())
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers(int page, int items) {
        Pageable pageable = buildPageRequest(page, items);
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> users = userPage.getContent();
        return users.stream()
                .map(UserServiceImpl::buildGetUserResponse)
                .toList();

    }

    @Override
    public ApiResponse<?> deleteUserById(int id) {
        userRepository.deleteById(id);
        return ApiResponse.builder()
                .message("User deleted successfully")
                .build();
    }

    @Override
    public void deleteAll() {
    userRepository.deleteAll();
    }

    @Override
    public UserResponse updateUserDetails(int id, UpdateUserRequest updateUserRequest) {
    User existingUser = userRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("User does not exist with id: " +id));
        existingUser.setFirstName(updateUserRequest.getFirstName());
        existingUser.setLastName(updateUserRequest.getLastName());
        existingUser.setEmail(updateUserRequest.getEmail());
        userRepository.save(existingUser);
        return buildUpdateUserDetails(existingUser);
    }

    private UserResponse buildUpdateUserDetails(User existingUser) {
        return modelMapper.map(existingUser, UserResponse.class);
    }

}
