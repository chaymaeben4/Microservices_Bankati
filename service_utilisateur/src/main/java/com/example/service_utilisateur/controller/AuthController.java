package com.example.service_utilisateur.controller;//package controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> authenticate(@RequestParam String email, @RequestParam String password) {
//        Authentication authentication = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(email, password)
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Génération du token JWT
//        String token = service.generateToken(authentication);
//
//        return ResponseEntity.ok(token);
//    }
//}
