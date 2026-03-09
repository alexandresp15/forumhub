package com.alexandresp15.forumhub.controller;

import com.alexandresp15.forumhub.dto.DadosLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<?> login(@RequestBody DadosLogin dados) {
        return ResponseEntity.ok("Autenticação realizada. Use Basic Auth para acessar /topicos.");
    }
}