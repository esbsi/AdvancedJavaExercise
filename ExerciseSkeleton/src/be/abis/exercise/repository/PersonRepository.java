package be.abis.exercise.repository;

import be.abis.exercise.model.Person;

public interface PersonRepository {

    Person findPersonByListIndex(int i);

}
