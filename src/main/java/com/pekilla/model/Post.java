package com.pekilla.model;

import com.pekilla.enums.Category;
import com.pekilla.model.constraint.PostConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Check;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Post extends ForumTable implements PostConstraint {
    @Size(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH, message = TITLE_LENGTH_ERROR)
    @Check(constraints = "LENGTH(title) >= "+TITLE_MIN_LENGTH)
    @NotBlank
    private String title;

    @Size(min = CONTENT_MIN_LENGTH, max = CONTENT_MAX_LENGTH, message = CONTENT_LENGTH_ERROR)
    @Check(constraints = "LENGTH(description) >= "+CONTENT_MIN_LENGTH)
    @NotBlank
    private String description;

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
