package com.pekilla.dto;

import com.pekilla.model.User;
import lombok.Builder;

@Builder
public record UserInfoDTO (
        String username,
        String link)
{
    public static UserInfoDTO from(User user) {
        return UserInfoDTO
                .builder()
                    .username(user.getUsername())
                    .link(user.getLink())
                .build();
    }
}
