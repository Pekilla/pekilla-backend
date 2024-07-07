package com.pekilla.service;

import com.pekilla.dto.CustomCategoryViewDTO;
import com.pekilla.exception.type.CategoryNotFoundException;
import com.pekilla.model.CustomCategory;
import com.pekilla.repository.CustomCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class CustomCategoryService {
    private final CustomCategoryRepository categoryRepository;
    private final UserService userService;

    public List<String> getAllNames() {
        return categoryRepository.findAll().stream()
            .map(CustomCategory::getName)
            .toList();
    }

    public List<CustomCategoryViewDTO> getAll() {
        return categoryRepository.findAll().stream()
            .map(CustomCategoryViewDTO::fromCategory)
            .toList();
    }

    public CustomCategoryViewDTO getByName(String name) {
        return CustomCategoryViewDTO.fromCategory(
            categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new)
        );
    }

    public String createCategory(CustomCategoryViewDTO dto) {
        categoryRepository.save(CustomCategory
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
