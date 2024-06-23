package com.pekilla.model;

import com.pekilla.enums.Category;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Post extends ForumTable {
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, length = 500)
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User originalPoster;
}
