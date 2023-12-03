package com.orchestra.service.orchestraservice.controller;

import com.orchestra.service.orchestraservice.entity.Person;
import com.orchestra.service.orchestraservice.entity.ProposedTrip;
import com.orchestra.service.orchestraservice.service.PersonService;
import com.orchestra.service.orchestraservice.service.ProposedTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProposedTripController {
    @Autowired
    private ProposedTripService proposedTripService;
    @Autowired
    private PersonService personService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/trips")
    public List<ProposedTrip> getAllTrips() {
        return proposedTripService.getTrips();
    }

    //Get specific trip
    @GetMapping("/tripById/{tripId}/")
    public ProposedTrip getTripByTripId(@PathVariable int tripId) {
        return proposedTripService.getTripByTripID(tripId);
    }

    @GetMapping("/tripsByCurrentUser/{userId}")
    public List<ProposedTrip> getTripsCreatedByUser(@PathVariable int userId) {
        return proposedTripService.getTripsCreatedByUser(userId);
    }

    @GetMapping("/getWeather/{location}")
    public Map<String, Object> showWeather(@PathVariable String location) {
        Map<String, Object> weatherMap = getWeather(location);
        List<Map<String, Object>> daysList = (List<Map<String, Object>>) weatherMap.get("days");
        Map<String, Object> weather = daysList.get(0);
        String date = (String) weather.get("datetime");
        Double temperature = (Double) weather.get("temp");
        String currentCondition = (String) weather.get("conditions");
        String prediction = (String) weather.get("description");

        Map<String, Object> updatedWeather = new HashMap<>();
        updatedWeather.put("date", date);
        updatedWeather.put("temperature", temperature);
        updatedWeather.put("currentCondition", currentCondition);
        updatedWeather.put("prediction", prediction);
        return updatedWeather;
    }

    @PostMapping("/createTrip")
    public ProposedTrip createProposedTrip(@RequestBody ProposedTrip proposedTrip) {
//        String location = proposedTrip.getLocation();
        int tripId = (int) (getRandomNumberForTrip() / Math.random() * 10);
        proposedTrip.setDateAndTime(LocalDateTime.now());
        proposedTrip.setTripID(tripId);

        System.out.println("FINDING PERSON::");
        System.out.println(proposedTrip.getUserID());

        Person person = personService.findPersonByUserID(proposedTrip.getUserID());
        person.getProposedTrips().add(proposedTrip);
        personService.updatePerson(person);
//        proposedTrip.setUser(person);

        return proposedTripService.createTrip(proposedTrip);
    }


    //
    @PutMapping("/updateTrip")
    public ProposedTrip updateTrip(@RequestBody ProposedTrip proposedTrip) {

        return proposedTripService.updateTrip(proposedTrip);
    }

    @GetMapping("/weather")
    public Map<String, Object> getWeather(String location) {
        String apiUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + location + "?unitGroup=metric&key=AE4QYPMES7EJ8FKZNNJAFRMGB&contentType=json";
        Map<String, Object> weather = restTemplate.getForObject(apiUrl, Map.class);
//        System.out.println("The weather is: ");
//        System.out.println(weather);
        return weather;
    }

    @GetMapping("/randomNumberForTrip")
    public int getRandomNumberForTrip() {

        String apiUrl = "http://www.randomnumberapi.com/api/v1.0/random?min=1&max=10000&count=1";
        Integer[] result = restTemplate.getForObject(apiUrl, Integer[].class);

        return result[0];
    }
}
