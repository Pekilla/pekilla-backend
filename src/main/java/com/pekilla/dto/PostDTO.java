package com.pekilla.dto;

import com.pekilla.enums.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
public record PostDTO(Long id, String title, String content, List<String> tags, Category category) {

}
