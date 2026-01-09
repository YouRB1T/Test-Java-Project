package com.zhkh.userservice.controller;

import com.zhkh.userservice.api.UserRequest;
import com.zhkh.userservice.api.UserResponse;
import com.zhkh.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Операции с пользователями")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(
            summary = "Создать пользователя",
            description = "Создание нового пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Пользователь создан"),
            @ApiResponse(responseCode = "400", description = "Неверные данные")
    })
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        UserResponse response = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Получить всех пользователей",
            description = "Получение списка всех пользователей"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно")
    })
    public ResponseEntity<List<UserResponse>> readAllUsers() {
        List<UserResponse> users = userService.readAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userID}")
    @Operation(
            summary = "Получить пользователя по ID",
            description = "Получение пользователя по идентификатору"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Не найдено")
    })
    public ResponseEntity<UserResponse> readUserById(@PathVariable UUID userID) {
        UserResponse response = userService.readUserById(userID);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userID}")
    @Operation(
            summary = "Обновить пользователя",
            description = "Обновление данных пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Не найдено")
    })
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable UUID userID,
            @RequestBody UserRequest userRequest) {
        UserResponse response = userService.updateUser(userID, userRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userID}")
    @Operation(
            summary = "Удалить пользователя",
            description = "Удаление пользователя по идентификатору"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешно"),
            @ApiResponse(responseCode = "404", description = "Не найдено")
    })
    public ResponseEntity<UserResponse> deleteUser(@PathVariable UUID userID) {
        UserResponse response = userService.deleteUser(userID);
        return ResponseEntity.ok(response);
    }
}
