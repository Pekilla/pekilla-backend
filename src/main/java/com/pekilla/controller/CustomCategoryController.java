package com.pekilla.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("${ALLOWED_URL}")
@RequestMapping("/categories")
@RestController()
public class CustomCategoryController {

    @GetMapping("/{name}")
    public ResponseEntity<Object> getCategory(@PathVariable String name) {
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
