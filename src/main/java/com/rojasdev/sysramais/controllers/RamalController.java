package com.rojasdev.sysramais.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rojasdev.sysramais.dto.LoginRequest;
import com.rojasdev.sysramais.services.RamalService;

import java.util.Map;

@RestController
@RequestMapping("/api/ramais")
public class RamalController {

    @Autowired
    private RamalService ramalService;

    @GetMapping("/disponivel")
    public ResponseEntity<Map<String, String>> getAvailableExtension() {
        return ResponseEntity.ok(ramalService.getAvailableExtension());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(ramalService.login(request.getUsuarioNome(), request.getExtension()));
    }


    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestBody Map<String, String> request) {
        String extension = request.get("extension");
        return ResponseEntity.ok(ramalService.logout(extension));
    }
}

