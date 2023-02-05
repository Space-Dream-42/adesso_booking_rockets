package com.example.spacedream.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Account {
    @Id
    @Column
    private String userName;
    @Column
    private String password;
}
