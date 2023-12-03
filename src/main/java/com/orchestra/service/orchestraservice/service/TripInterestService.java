package com.orchestra.service.orchestraservice.service;

import com.orchestra.service.orchestraservice.entity.TripInterest;
import com.orchestra.service.orchestraservice.repository.TripInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripInterestService {
    @Autowired
    private TripInterestRepository tripInterestRepository;


    public TripInterest expressTripInterest(TripInterest tripInterest) {
        return tripInterestRepository.save(tripInterest);
    }

    //Allows trip creators etc to know the interests expressed for a particular trip
    public List<TripInterest> findInterestForSpecificTrip(int tripId) {
        return tripInterestRepository.findInterestsForSpecificTripByTripID(tripId);
    }

    //Allows users to know the trips they have expressed interest in
    public List<TripInterest> findTripInterestsExpressedByUserId(int userId) {
        return tripInterestRepository.findTripInterestsExpressedByInterestedPersonId(userId);
    }
}
