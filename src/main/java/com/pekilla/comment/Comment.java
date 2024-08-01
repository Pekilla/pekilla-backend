package com.pekilla.comment;

import com.pekilla.global.ForumTable;
import com.pekilla.post.Post;
import com.pekilla.customer.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Comment extends ForumTable {
    @Column(nullable = false, length = 3000)
    private String message;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer author;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime addedDate = LocalDateTime.now();
}
