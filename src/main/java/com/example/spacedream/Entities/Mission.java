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
public class Mission {

    @Column
    private String missionName;
    @Column
    private String startTime;
    @Column
    private String liftOffDate;
    @Column
    private String startPlanet;
    @Column
    private String targetPlanet;
    @Column
    private float duration;
    @Column
    private String estimatedTimeOfArrival;
    @Column
    private float ticketPrice;
    @Column
    private int amountOfTickets;
    @Column
    private String startingPlace;
    @Column
    private String rocketName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Spezifiziert die strategie, wie die id's generiert werden. Diese werden automatisch verwaltet.
    @Column
    private Integer id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "spacemanager_userName", nullable = false, referencedColumnName = "userName")
    private Spacemanager spacemanager;
    @OneToMany(mappedBy = "mission")
    private List<Customer> customers;
}
