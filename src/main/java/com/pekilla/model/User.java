package com.pekilla.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class User extends ForumTable {
    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false)
    private String password;
}
