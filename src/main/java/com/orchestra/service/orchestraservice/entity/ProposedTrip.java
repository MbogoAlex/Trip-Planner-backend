package com.orchestra.service.orchestraservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROPOSED_TRIPS_TBL")
public class ProposedTrip {
    @Id
    @GeneratedValue
    private int id;
    private int userID;
    @Column(unique = true)
    private int tripID;
    private String userName;
    private String message;
    private LocalDateTime dateAndTime;
    private String location;
    @Transient
    private Map<String, Object> weather;

//    @ManyToOne
//    @JoinColumn(name = "fk_user_id", referencedColumnName = "userID")
//    private Person user;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_trip_id", referencedColumnName = "tripID")
    private List<TripInterest> tripInterests;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_trip_id", referencedColumnName = "tripID")
//    ProposedTripInterests proposedTripInterests;

}


