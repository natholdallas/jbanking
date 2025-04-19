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
public final class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(targetEntity = Card.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false)
    public Card card;

    @Column(nullable = false, length = 20)
    public String target;

    @JsonFormat(shape = Shape.STRING)
    @Column(nullable = false)
    public BigDecimal amount;

}
