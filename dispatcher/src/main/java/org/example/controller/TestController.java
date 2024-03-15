package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.dto.UserUpdateDto;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@RequestParam(name = "id") String id){
        return userRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new RuntimeException("User not found!"));
    }

    @PostMapping("/user")
    public User addUser(@RequestBody UserDto userDto){
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
        return userRepository.save(user);
    }

    @PostMapping("/user/update/{id}")
    public User updateUser(@RequestBody UserUpdateDto userUpdateDto,
                           @RequestParam(name = "id") String id){
        User user = getUser(id);
        user.setServerUrl(userUpdateDto.getServerUrl());
        user.setIngressId(userUpdateDto.getIngressId());
        user.setStreamKey(userUpdateDto.getStreamKey());

        return user;
    }
    @GetMapping
    public String test(){
        return "All works!";
    }
}
