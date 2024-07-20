package com.pekilla.tag;

import com.pekilla.global.ForumTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Tag extends ForumTable {
    @NotBlank
    @Column(unique = true)
    private String content;
}
