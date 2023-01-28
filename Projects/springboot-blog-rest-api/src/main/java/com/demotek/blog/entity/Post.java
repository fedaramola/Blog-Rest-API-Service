package com.demotek.blog.entity;

import lombok.*;


import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "pst_sequence")


    @Id
    @Column(name = "id")

    //@GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
   private Set<Comment> comments = new HashSet<>();

}
