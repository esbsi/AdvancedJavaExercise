package be.abis.exercise.test;

import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class TestPerson {

    PersonRepository personRepository = new FilePersonRepository();
    Person person2 = personRepository.findPersonByListIndex(1);
    Person person3 = personRepository.findPersonByListIndex(2);
    Person person4 = personRepository.findPersonByListIndex(3);


    @Test
    public void personsShouldBeEqual(){
        assert person3.equals(person4);
    }

    @Test
    public void personsShouldNotBeSame(){
        assert person3 != person4;
    }

    @Test
    public void testHashSet(){
        Set personSet = new HashSet();
        personSet.add(person2);
        personSet.add(person3);
        personSet.add(person4);
        System.out.println(personSet);
        assert personSet.size() == 2;
    }

    @Test
    public void ageShouldBe64(){
        System.out.println("Birthdate: " + person2.getBirthDate());
        int age = person2.calculateAge();
        System.out.println(age);
        assert age == 64;
    }



}
