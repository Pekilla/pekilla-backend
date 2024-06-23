package com.pekilla.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostDTO {
    
    private String title;
    private String content;
}
