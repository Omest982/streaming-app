package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AuthRequest;
import org.example.dto.RegisterRequest;
import org.example.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/auth")
public class AuthController {
    private  final AuthService authService;
    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest){
        return authService.authenticate(authRequest);
    }

    @PostMapping("/register")
    public UUID register(@RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam(name = "token") String token){
        return authService.validateToken(token);
    }
}
