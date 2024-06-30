package com.pekilla.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class User extends ForumTable {
    @Size(max = 30)
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    // It will be a string of 9 char. Not the complete url.
    @NotBlank
    @Size(min = 9, max = 9)
    @Column(unique = true)
    private String link;
}
