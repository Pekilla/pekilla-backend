package com.pekilla.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("${ALLOWED_URL}")
@RestController("/category")
public class CategoryController {


    @GetMapping("/{name}")
    public ResponseEntity<Object> getCategory(@PathVariable String name) {
        try {
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
