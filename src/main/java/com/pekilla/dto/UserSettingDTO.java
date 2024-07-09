package com.pekilla.dto;

import com.pekilla.model.User;

public record UserSettingDTO(
    String email,
    String username,
    String icon,
    String banner
) {
    public static UserSettingDTO from(User user) {
        return new UserSettingDTO(
            user.getEmail(),
            user.getUsername(),
            user.getIcon(),
            user.getBanner()
        );
    }
}
