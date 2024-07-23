package com.pekilla.category;

import com.pekilla.category.dto.CategoryViewDTO;
import com.pekilla.category.dto.EditCreateCategoryDTO;
import com.pekilla.category.exception.CategoryNotFoundException;
import com.pekilla.upload.FileService;
import com.pekilla.upload.enums.FileType;
import com.pekilla.user.User;
import com.pekilla.user.UserService;
import com.pekilla.user.exception.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public ResponseEntity<?> createOrUpdate(@Valid EditCreateCategoryDTO categoryDTO, boolean isCreate) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Category category = (isCreate ? new Category() : categoryRepository.findOneByName(categoryDTO.name())
                .orElseThrow(CategoryNotFoundException::new));
            category.setDescription(categoryDTO.description());

            // Change the icon
            if (categoryDTO.icon() == null) {
                category.setIcon(null);
            } else if(categoryDTO.icon() instanceof MultipartFile icon) {
                category.setIcon(fileService.saveFile(icon, FileType.CATEGORY_ICON));
            }

            // Change the banner
            if (categoryDTO.banner() == null) {
                category.setBanner(null);
            } else if(categoryDTO.banner() instanceof MultipartFile banner) {
                category.setBanner(fileService.saveFile(banner, FileType.CATEGORY_BANNER));
            }

            if (isCreate) {
                category.setName(categoryDTO.name());
                category.setCreator(user);
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

    public ResponseEntity<?> getEditCategory(String name) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var dto = this.getByName(name);

        if(dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }

        if(user.getId() != dto.creatorId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to edit this category.");
        }

        else {
            return ResponseEntity.ok().body(
                new EditCreateCategoryDTO(
                    dto.name(),
                    fileService.getImageUrl(dto.banner(), FileType.CATEGORY_BANNER),
                    fileService.getImageUrl(dto.icon(), FileType.CATEGORY_ICON),
                    dto.description()
                )
            );
        }
    }
}
