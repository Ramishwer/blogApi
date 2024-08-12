package com.blog.blog.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    @Id
    private Integer id;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
