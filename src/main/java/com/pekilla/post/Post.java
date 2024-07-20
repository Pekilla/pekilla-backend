package com.pekilla.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pekilla.category.Category;
import com.pekilla.global.ForumTable;
import com.pekilla.tag.Tag;
import com.pekilla.post.constraint.PostConstraint;
import com.pekilla.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


@EqualsAndHashCode(callSuper = true)
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

    @ManyToOne
    private Category category;

    @NotNull
    @ManyToOne
    private User originalPoster;

    //private long likes = 1;

    @Column(columnDefinition = "DATETIME DEFAULT NOW()", nullable = false)
    private LocalDateTime addedDate;

    @Column(columnDefinition = "DATETIME DEFAULT NOW()", nullable = false)
    private LocalDateTime lastModifiedDate;

    @ManyToMany
    @JoinTable(
        name = "rel_post_tag",
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    @JsonIgnore
    public Set<String> getTagContents() {
        return tags
            .stream()
            .map(Tag::getContent)
            .collect(Collectors.toSet());
    }

    public void setDatesForCreate() {
        this.addedDate = LocalDateTime.now();
        this.lastModifiedDate = this.addedDate;
    }
}
