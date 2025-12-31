package com.zhkh.userservice.service.impl;

import com.zhkh.userservice.api.UserRequest;
import com.zhkh.userservice.api.UserResponse;
import com.zhkh.userservice.model.User;
import com.zhkh.userservice.repository.UserRepository;
import com.zhkh.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public UserResponse create(UserRequest request) {
        User user = new User(
                UUID.randomUUID(),
                request.getUserName(),
                request.getEmail(),
                request.getPassword(),
        );
        return null;
    }

    @Override
    public List<UserResponse> readAllUsers() {
        return List.of();
    }

    @Override
    public UserResponse readUserById(UUID userID) {
        return null;
    }

    @Override
    public UserResponse updateUser(UUID userID, UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse deleteUser(UUID userID) {
        return null;
    }
}
