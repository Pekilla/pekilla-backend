package com.pekilla.user.dto;

public record UserSettingDTO (
    String email,
    String username,
    String icon,
    String banner
) { }
