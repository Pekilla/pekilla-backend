package com.pekilla.controller;

import com.pekilla.dto.CategoryViewDTO;
import com.pekilla.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("${ALLOWED_URL}")
@RequestMapping("/api/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames() {
        return ResponseEntity.ok(categoryService.getAllNames());
    }

    @GetMapping
    public ResponseEntity<List<CategoryViewDTO>> getAllCategories() {
        try {
            return ResponseEntity.ok(categoryService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryViewDTO> getCategory(@PathVariable String name) {
        try {
            return ResponseEntity.ok(categoryService.getByName(name));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createCategory(CategoryViewDTO dto) {
        try {
            return ResponseEntity.ok(categoryService.createCategory(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cannot create that category");
        }
    }
}
