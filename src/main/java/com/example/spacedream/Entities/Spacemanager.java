package com.example.spacedream.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Spacemanager extends Account {
    @Column
    private String spacelineName;
    @OneToMany(mappedBy = "spacemanager")
    private List<Mission> missions;


    public Spacemanager(String userName, String password, String spacelineName, List<Mission> missions) {
        super(userName, password);
        this.spacelineName = spacelineName;
        this.missions = missions;
    }
}
