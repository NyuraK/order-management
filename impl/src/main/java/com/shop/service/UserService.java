package com.shop.service;

import com.shop.api.swagger.models.OrderDto;
import com.shop.api.swagger.models.UserDto;
import com.shop.exception.UserNotFoundException;
import com.shop.model.User;
import com.shop.repository.OrderRepository;
import com.shop.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private UserRepository repository;
    private OrderRepository orderRepository;

    @Autowired
    public UserService(UserRepository repository, OrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    public UserDto getUser(String id) {
        log.info("Get user by id {}", id);
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return Converter.convertToDto(user);
    }

    public List<UserDto> getAllUsers() {
        log.info("Get all users");
        return repository.findAll().stream().map(Converter::convertToDto).collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto) {
        log.info("Create new user");
        User user = Converter.convertToEntity(userDto);
        return Converter.convertToDto(repository.save(user));
    }

    public UserDto updateUser(UserDto userDto) {
        log.info("Update user with id {}", userDto.getId());
        User user = repository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException(userDto.getId()));
        repository.save(Converter.convertToEntity(userDto));
        return Converter.convertToDto(user);
    }

    public void deleteUser(String id) {
        log.info("Delete user with id {}", id);
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        repository.delete(user);
    }

    public List<OrderDto> getAllUserOrders(String id) {
        return orderRepository.findOrdersByCustomerId(id).stream().map(Converter::convertToDto).collect(Collectors.toList());
    }
}
