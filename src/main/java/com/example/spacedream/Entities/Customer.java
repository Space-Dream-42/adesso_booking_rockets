package com.example.spacedream.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Customer extends Account {
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private int experience;
    @Column
    private int cashBalance;
    @ManyToOne
    @JoinColumn(name = "mission_id", nullable = true, referencedColumnName = "id")
    private Mission mission;
}
