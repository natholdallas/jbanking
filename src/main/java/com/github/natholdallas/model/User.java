package com.github.natholdallas.model;

import com.github.natholdallas.common.Permission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 20, unique = true)
    public String username;

    @Column(nullable = false, length = 20)
    public String password;

    @Column(length = 10)
    public String idName;

    @Column(length = 18)
    public String idCard;

    @Column(nullable = false)
    public Permission permission;

}
