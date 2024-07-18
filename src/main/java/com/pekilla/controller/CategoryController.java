package com.pekilla.controller;

import com.pekilla.dto.CategoryViewDTO;
import com.pekilla.dto.EditCreateCategoryDTO;
import com.pekilla.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
            return ResponseEntity.ok(categoryService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<?> createCategory(
        @RequestParam(required = false) MultipartFile banner,
        @RequestParam(required = false) MultipartFile icon,
        EditCreateCategoryDTO dto
    ) {
        return categoryService.createOrUpdate(icon, banner, dto,true);
    }

    @PatchMapping
    public ResponseEntity<?> updateCategory(
        @RequestParam(required = false) MultipartFile banner,
        @RequestParam(required = false) MultipartFile icon,
        EditCreateCategoryDTO dto
    ) {
        return categoryService.createOrUpdate(icon, banner, dto,false);
    }

    @GetMapping("/exists/{name}")
    public boolean isExists(@PathVariable String name) {
        return categoryService.isExists(name);
    }
}
