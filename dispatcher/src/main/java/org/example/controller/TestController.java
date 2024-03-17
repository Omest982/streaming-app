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

    @GetMapping("/user")
    public User getUserById(@RequestParam(name = "id") String id){
        return userService.getUserById(UUID.fromString(id));
    }

    @GetMapping("/user")
    public User getUserByUsername(@RequestParam(name = "username") String username){
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

    @PostMapping("/user/update")
    public UUID updateUser(@RequestBody UserUpdateDto userUpdateDto,
                           @RequestParam(name = "id") String id){
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
