package com.pekilla.dto;

import lombok.Builder;

@Builder
public record UserInfoDTO (
        String username,
        String icon,
        String banner
) { }
