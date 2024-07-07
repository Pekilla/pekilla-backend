package com.pekilla.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class User extends ForumTable {
    @Size(max = 30)
    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;
}
