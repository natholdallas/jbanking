package com.github.natholdallas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(targetEntity = User.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false)
    public User user;

    @Column(nullable = false, length = 20)
    public String title;

    @Column(nullable = false, length = 50)
    public String content;

    @Column(nullable = false)
    public boolean closed;

    @OneToMany(mappedBy = "comment", targetEntity = Reply.class)
    public List<Reply> replies;

}
