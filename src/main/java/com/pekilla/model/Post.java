package com.pekilla.model;

import com.pekilla.enums.Category;
import com.pekilla.model.constraint.PostConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Check;
import java.sql.Date;
import java.util.List;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
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

    @NotNull
    @Column(columnDefinition = "DATETIME DEFAULT NOW()")
    private Date addedDate = new Date(System.currentTimeMillis());

    @ManyToMany
    @JoinTable(name = "rel_post_tag")
    private Set<Tag> tags;
}
