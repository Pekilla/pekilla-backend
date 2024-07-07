package com.pekilla.service;


import com.pekilla.dto.CustomCategoryViewDTO;
import com.pekilla.exception.type.CategoryNotFoundException;
import com.pekilla.model.CustomCategory;
import com.pekilla.repository.CustomCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class CustomCategoryService {

    private final CustomCategoryRepository categoryRepository;
    private final UserService userService;

    public List<CustomCategoryViewDTO> getAll() {
        return categoryRepository
                .findAll().stream().map(
                        customCategory -> CustomCategoryViewDTO
                            .builder()
                                .name(customCategory.getName())
                                .description(customCategory.getDescription())
                                .creatorId(customCategory.getCreator().getId())
                            .build()).collect(Collectors.toList());
    }

    public CustomCategoryViewDTO getByName(String name) {
        CustomCategory category = categoryRepository.findByName(name)
                .orElseThrow(CategoryNotFoundException::new);

        return CustomCategoryViewDTO
                .builder()
                    .name(category.getName())
                    .description(category.getDescription())
                    .creatorId(category.getCreator().getId())
                .build();
    }

    public String createCategory(CustomCategoryViewDTO dto) {
        categoryRepository.save(CustomCategory
                .builder()
                    .creator(userService.getUserById(dto.creatorId()))
                    .name(dto.name())
                    .description(dto.description())
                    // DEFAULT ICON AND BANNER SHOULD BE THERE
                    .banner(null)
                    .icon(null)
                .build());
        return "Category has been added with success";
    }
}
