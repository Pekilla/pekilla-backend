package com.pekilla.category;

import com.pekilla.category.dto.CategoryViewDTO;
import com.pekilla.category.dto.EditCreateCategoryDTO;
import com.pekilla.upload.enums.FileType;
import com.pekilla.upload.FileService;
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
    private final FileService fileService;

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

    // Separated method because this one will receive verification when Spring security is installed.
    @GetMapping("/edit")
    public EditCreateCategoryDTO getEditCategory(@RequestParam String name) {
        var dto = categoryService.getByName(name);
        System.out.println(dto);
        System.out.println(name);
        return new EditCreateCategoryDTO(
            dto.name(),
            fileService.getImageUrl(dto.banner(), FileType.CATEGORY_BANNER),
            fileService.getImageUrl(dto.icon(), FileType.CATEGORY_ICON),
            dto.description(),
            dto.creatorId()
        );
    }
}
