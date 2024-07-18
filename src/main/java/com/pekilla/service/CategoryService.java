package com.pekilla.service;

import com.pekilla.dto.CategoryViewDTO;
import com.pekilla.dto.EditCreateCategoryDTO;
import com.pekilla.enums.FileType;
import com.pekilla.exception.type.CategoryNotFoundException;
import com.pekilla.exception.type.UserNotFoundException;
import com.pekilla.model.Category;
import com.pekilla.repository.CategoryRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final FileService fileService;

    public List<String> getAllNames() {
        return categoryRepository.findAll().stream()
            .map(Category::getName)
            .toList();
    }

    public List<CategoryViewDTO> getAll() {
        return categoryRepository.findAll().stream()
            .map(CategoryViewDTO::fromCategory)
            .toList();
    }

    public CategoryViewDTO getByName(String name) {
        return CategoryViewDTO.fromCategory(
            categoryRepository.findOneByName(name)
                .orElseThrow(CategoryNotFoundException::new)
        );
    }

    public ResponseEntity<?> createOrUpdate(
        MultipartFile icon,
        MultipartFile banner,
        @Valid @NotNull EditCreateCategoryDTO categoryDTO,
        boolean isCreate
    ) {
        try {
            Category category = (isCreate ? new Category() : categoryRepository.findOneByName(categoryDTO.name())
                .orElseThrow(CategoryNotFoundException::new));
            category.setDescription(categoryDTO.description());

            if (icon != null) category.setIcon(fileService.saveFile(icon, FileType.CATEGORY_ICON));
            if (banner != null) category.setBanner(fileService.saveFile(icon, FileType.CATEGORY_BANNER));

            if (isCreate) {
                category.setName(categoryDTO.name());
                category.setCreator(userService.getUserById(categoryDTO.creatorId()));
            }

            categoryRepository.save(category);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Category already exists");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Invalid image format.");
        }
    }

    public boolean isExists(String name) {
        return categoryRepository.isExistsByName(name) == 1;
    }
}
