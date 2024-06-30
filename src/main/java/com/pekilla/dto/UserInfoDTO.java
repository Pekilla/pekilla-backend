package com.pekilla.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfoDTO {

    private String password;
    private String username;
}
