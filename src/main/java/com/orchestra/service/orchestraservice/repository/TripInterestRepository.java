package com.orchestra.service.orchestraservice.repository;

import com.orchestra.service.orchestraservice.entity.Person;
import com.orchestra.service.orchestraservice.entity.TripInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripInterestRepository extends JpaRepository<TripInterest, Integer> {



    List<TripInterest> findInterestsForSpecificTripByTripID(int tripId);

    List<TripInterest> findTripInterestsExpressedByInterestedPersonId(int userId);
}
