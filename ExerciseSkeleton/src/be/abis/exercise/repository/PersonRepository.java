package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;

public interface PersonRepository {

    Person findPersonByListIndex(int i);

    Person findPersonById(int id) throws PersonNotFoundException;

}
