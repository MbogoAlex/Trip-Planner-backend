package com.orchestra.service.orchestraservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRIP_INTERESTS")
public class TripInterest {
    @Id
    @GeneratedValue
    private int id;
    private int tripID;

    private LocalDateTime localDateTime;

    private int interestedPersonId;
    private String proposerUserName;
    private String interestedPersonUserName;
    private String interestedPersonMessage;

}


