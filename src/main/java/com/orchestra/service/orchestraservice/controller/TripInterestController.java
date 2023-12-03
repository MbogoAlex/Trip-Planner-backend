package com.orchestra.service.orchestraservice.controller;

import com.orchestra.service.orchestraservice.entity.Person;
import com.orchestra.service.orchestraservice.entity.ProposedTrip;
import com.orchestra.service.orchestraservice.entity.TripInterest;
import com.orchestra.service.orchestraservice.service.PersonService;
import com.orchestra.service.orchestraservice.service.ProposedTripService;
import com.orchestra.service.orchestraservice.service.TripInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
public class TripInterestController {
    @Autowired
    private TripInterestService tripInterestService;
    @Autowired
    private ProposedTripService proposedTripService;
    @Autowired

    private PersonService personService;

    @PostMapping("/expressTripInterest")
    public TripInterest expressTripInterest(@RequestBody TripInterest tripInterest) {
        tripInterest.setLocalDateTime(LocalDateTime.now());
        ProposedTrip trip = proposedTripService.getTripByTripID(tripInterest.getTripID());
        trip.getTripInterests().add(tripInterest);
        Person person = personService.findPersonByUserID(tripInterest.getInterestedPersonId());
        person.getTripsInterestedIn().add(tripInterest);
        personService.updatePerson(person);

        return tripInterestService.expressTripInterest(tripInterest);
    }

    @GetMapping("/findSpecificTripInterests/{tripId}")
    public List<TripInterest> findTripInterests(@PathVariable int tripId) {
        return tripInterestService.findInterestForSpecificTrip(tripId);
    }

    @GetMapping("/findTripInterestsExpressedByUser/{userId}")
    public List<TripInterest> findTripInterestsExpressedByUser(@PathVariable int userId) {
        return tripInterestService.findTripInterestsExpressedByUserId(userId);
    }
}
