package com.orchestra.service.orchestraservice.controller;

import com.orchestra.service.orchestraservice.config.RestTemplateConfig;
import com.orchestra.service.orchestraservice.entity.Person;
import com.orchestra.service.orchestraservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class PersonController {
    @Autowired
    private PersonService service;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/findPerson/{userId}/")
    public Person findPersonById(@PathVariable int userId) {
        return service.findPersonByUserID(userId);

    }

    @PostMapping("/createAccount")
    public Person createAccount(@RequestBody Person person) {
        System.out.println("CREATING PERSON ACCOUNT");
        int userId = (int) (getRandomNumberForPerson() / Math.random() * 10);
        person.setUserID(userId);
        System.out.println("NEW PERSON:");
        System.out.println(person.toString());
        return service.createAccount(person);
    }

    @GetMapping("/randomNumberForPerson")
    public int getRandomNumberForPerson() {

        String apiUrl = "http://www.randomnumberapi.com/api/v1.0/random?min=1&max=10000&count=1";
        Integer[] result = restTemplate.getForObject(apiUrl, Integer[].class);

        return result[0];
    }
}
