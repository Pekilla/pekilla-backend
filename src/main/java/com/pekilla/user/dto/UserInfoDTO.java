package com.pekilla.user.dto;

import lombok.Builder;

@Builder
public record UserInfoDTO (
        String username,
        String icon,
        String banner
) { }
