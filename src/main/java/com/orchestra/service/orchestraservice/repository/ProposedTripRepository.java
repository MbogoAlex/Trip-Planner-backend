package com.orchestra.service.orchestraservice.repository;

import com.orchestra.service.orchestraservice.entity.ProposedTrip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposedTripRepository extends JpaRepository<ProposedTrip, Integer> {

    List<ProposedTrip> findTripsCreatedByUserID(int userID);

    ProposedTrip findTripByTripID(int tripID);
}
