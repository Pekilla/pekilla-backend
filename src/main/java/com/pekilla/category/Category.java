package com.pekilla.category;

import com.pekilla.global.ForumTable;
import com.pekilla.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends ForumTable {
    @Column(unique = true)
    private String name;

    // File name of the icon stored in the FileSystemStorage
    private String banner;
    private String icon;

    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User creator;
}
