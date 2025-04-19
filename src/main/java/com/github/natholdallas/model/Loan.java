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
public final class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(targetEntity = Card.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(nullable = false)
    public Card card;

    @JsonFormat(shape = Shape.STRING)
    @Column(nullable = false)
    public BigDecimal amount;

    @Column(nullable = false)
    public boolean active;

}
