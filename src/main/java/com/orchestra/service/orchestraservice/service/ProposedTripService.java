package com.orchestra.service.orchestraservice.service;

import com.orchestra.service.orchestraservice.entity.ProposedTrip;
import com.orchestra.service.orchestraservice.repository.ProposedTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposedTripService {
    @Autowired
    private ProposedTripRepository proposedTripRepository;

    public ProposedTrip createTrip(ProposedTrip proposedTrip) {
        return proposedTripRepository.save(proposedTrip);
    }


    public ProposedTrip updateTrip(ProposedTrip updatedTrip) {
        ProposedTrip tripToUpdate = proposedTripRepository.findById(updatedTrip.getTripID()).orElse(null);
        tripToUpdate.setTripInterests(updatedTrip.getTripInterests());
        tripToUpdate.setMessage(updatedTrip.getMessage());
        tripToUpdate.setLocation(updatedTrip.getLocation());
        return proposedTripRepository.save(tripToUpdate);
    }

    public List<ProposedTrip> getTrips(){
        return proposedTripRepository.findAll();
    }

    public ProposedTrip getTripByTripID(int tripID) {
        return proposedTripRepository.findTripByTripID(tripID);
    }

    public List<ProposedTrip> getTripsCreatedByUser(int userId) {
        return proposedTripRepository.findTripsCreatedByUserID(userId);
    }
}
