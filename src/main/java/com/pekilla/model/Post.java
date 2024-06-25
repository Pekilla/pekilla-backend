package com.pekilla.model;

import com.pekilla.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Post extends ForumTable {
    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 10, max = 500)
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull
    @ManyToOne
    private User originalPoster;

    @ManyToMany
    @JoinTable(name = "rel_post_tag")
    private List<Tag> tags;
}
