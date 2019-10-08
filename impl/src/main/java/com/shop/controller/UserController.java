package com.shop.controller;

import com.shop.api.swagger.controllers.UserManagementApi;
import com.shop.api.swagger.models.OrderDto;
import com.shop.api.swagger.models.UserDto;
import com.shop.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}")
@Api(tags = "User Management")
public class UserController implements UserManagementApi {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<UserDto> addUser(UserDto userDto) {
        return ResponseEntity.ok(service.createUser(userDto));
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String id) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(String id, UserDto userDto) {
        return ResponseEntity.ok(service.updateUser(userDto));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAllUserOrders(String id) {
        return ResponseEntity.ok(service.getAllUserOrders(id));
    }
}
