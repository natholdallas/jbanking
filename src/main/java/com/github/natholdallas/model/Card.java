package com.github.natholdallas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(targetEntity = User.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false)
    public User user;

    @Column(nullable = false, length = 18)
    public String number;

    @Column(nullable = false, length = 10)
    public String name;

    @JsonFormat(shape = Shape.STRING)
    @Column(nullable = false)
    public BigDecimal balance;

}
