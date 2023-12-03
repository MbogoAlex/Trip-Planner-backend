package com.orchestra.service.orchestraservice.repository;

import com.orchestra.service.orchestraservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByUserID(int userID);
}
