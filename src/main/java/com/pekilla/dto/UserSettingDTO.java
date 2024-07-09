package com.pekilla.dto;

import com.pekilla.model.User;

public record UserSettingDTO(
    String email,
    String username,
    String icon,
    String banner
) {
}
