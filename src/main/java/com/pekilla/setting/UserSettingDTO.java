package com.pekilla.setting;

public record UserSettingDTO (
    String email,
    String username,
    String icon,
    String banner
) { }
