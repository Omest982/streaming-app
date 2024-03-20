package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserResponseDto;
import org.example.entity.User;
import org.example.jwt.JwtService;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserInfoController {
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/{token}")
    public UserResponseDto getCurrentUser(@PathVariable(value = "token") String token){
        if (!jwtService.isTokenValid(token)){
            return null;
        }

        String username = jwtService.extractUsername(token);
        User user = userService.getUserByUsername(username);

        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }

}
