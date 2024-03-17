package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.dto.UserUpdateDto;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;

    @GetMapping("/user/id/{id}")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(UUID.fromString(id));
    }

    @GetMapping("/user/username/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping("/user")
    public UUID addUser(@RequestBody UserDto userDto){
        User user = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .isLive(userDto.isLive())
                .ingressId(userDto.getIngressId())
                .password(userDto.getPassword())
                .serverUrl(userDto.getServerUrl())
                .thumbnaiUrl(userDto.getThumbnaiUrl())
                .streamKey(userDto.getStreamKey())
                .build();
        return userService.saveUser(user);
    }

    @PostMapping("/user/update/{id}")
    public UUID updateUser(@RequestBody UserUpdateDto userUpdateDto,
                           @PathVariable String id){
        User user = getUserById(id);
        user.setServerUrl(userUpdateDto.getServerUrl());
        user.setIngressId(userUpdateDto.getIngressId());
        user.setStreamKey(userUpdateDto.getStreamKey());

        return userService.saveUser(user);
    }
    @GetMapping
    public String test(){
        return "All works!";
    }
}
