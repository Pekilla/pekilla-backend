package com.pekilla.service;

import com.pekilla.dto.CategoryViewDTO;
import com.pekilla.exception.type.CategoryNotFoundException;
import com.pekilla.model.Category;
import com.pekilla.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;

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
            categoryRepository.findOneByName(name).orElseThrow(CategoryNotFoundException::new)
        );
    }

    public String createCategory(CategoryViewDTO dto) {
        categoryRepository.save(Category
            .builder()
            .creator(userService.getUserById(dto.creatorId()))
            .name(dto.name())
            .description(dto.description())
            .banner(dto.banner())
            .icon(dto.icon())
            .build());
        return "Category has been added with success";
    }
}
