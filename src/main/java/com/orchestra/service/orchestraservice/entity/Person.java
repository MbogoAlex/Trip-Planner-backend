package com.orchestra.service.orchestraservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS_TBL")
public class Person {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private int userID;
    private String userName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "userID")
    private List<ProposedTrip> proposedTrips;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "interestedPersonId")
    private List<TripInterest> tripsInterestedIn;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_user_id", referencedColumnName = "proposerID")
//    private List<ProposedTripInterests> proposedTripInterests;
}
