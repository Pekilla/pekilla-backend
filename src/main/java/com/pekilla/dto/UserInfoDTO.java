package com.pekilla.dto;

import com.pekilla.model.User;
import lombok.Builder;

@Builder
public record UserInfoDTO (
        String username,
        String icon,
        String banner
) { }
