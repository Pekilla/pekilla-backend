package com.pekilla.category;

import com.pekilla.category.dto.CategoryViewDTO;
import com.pekilla.category.dto.EditCreateCategoryDTO;
import com.pekilla.upload.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
            return ResponseEntity.ok(categoryService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<?> createCategory(
        EditCreateCategoryDTO dto
    ) {
        return categoryService.createOrUpdate(dto,true);
    }

    @PatchMapping
    public ResponseEntity<?> updateCategory(
        EditCreateCategoryDTO dto
    ) {
        return categoryService.createOrUpdate(dto, false);
    }

    @GetMapping("/exists")
    public boolean isExists(@RequestParam String name) {
        System.out.println(name);
        return categoryService.isExists(name);
    }

    @GetMapping("/edit")
    public ResponseEntity<?> getEditCategory(@RequestParam String name) {
        return categoryService.getEditCategory(name);
    }
}
