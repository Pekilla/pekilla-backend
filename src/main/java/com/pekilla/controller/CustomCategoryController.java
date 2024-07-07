package com.pekilla.controller;

import com.pekilla.dto.CustomCategoryViewDTO;
import com.pekilla.service.CustomCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("${ALLOWED_URL}")
@RequestMapping("/api/categories")
@RestController
@RequiredArgsConstructor
public class CustomCategoryController {
    private final CustomCategoryService customCategoryService;

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames() {
        return ResponseEntity.ok(customCategoryService.getAllNames());
    }

    @GetMapping
    public ResponseEntity<List<CustomCategoryViewDTO>> getAllCategories() {
        try {
            return ResponseEntity.ok(customCategoryService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<CustomCategoryViewDTO> getCategory(@PathVariable String name) {
        try {
            return ResponseEntity.ok(customCategoryService.getByName(name));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createCategory(CustomCategoryViewDTO dto) {
        try {
            return ResponseEntity.ok(customCategoryService.createCategory(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cannot create that category");
        }
    }
}
