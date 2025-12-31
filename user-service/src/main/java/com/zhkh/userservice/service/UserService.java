package com.zhkh.userservice.service;

import com.zhkh.userservice.api.UserRequest;
import com.zhkh.userservice.api.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse create(UserRequest request);
    List<UserResponse> readAllUsers();
    UserResponse readUserById(UUID userID);
    UserResponse updateUser(UUID userID, UserRequest userRequest);
    UserResponse deleteUser(UUID userID);
}
