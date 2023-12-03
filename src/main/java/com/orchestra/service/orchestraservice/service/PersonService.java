package com.orchestra.service.orchestraservice.service;

import com.orchestra.service.orchestraservice.entity.Person;
import com.orchestra.service.orchestraservice.entity.ProposedTrip;
import com.orchestra.service.orchestraservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person createAccount(Person person) {

        return personRepository.save(person);
    }

    public Person findPersonByUserID(int userID){

        return personRepository.findByUserID(userID);
    }

    public Person updatePerson(Person person){
        Person personToUpdate = personRepository.findById(person.getId()).orElse(null);
        personToUpdate.setUserName(person.getUserName());
        personToUpdate.setProposedTrips(person.getProposedTrips());
        return personRepository.save(personToUpdate);

    }



}
