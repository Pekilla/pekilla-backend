package com.pekilla.upload.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FileType {
    USER_ICON("/users/icon"),
    USER_BANNER("/users/banner"),
    CATEGORY_ICON("/categories/icon"),
    CATEGORY_BANNER("/categories/banner");

    public final String folder;
}