package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

public class TestFilePersonRepository {

    Logger logger = LogManager.getLogger();
    Logger exceptionLogger = LogManager.getLogger("exceptionLogger");


    FilePersonRepository filePersonRepository = new FilePersonRepository();

    @BeforeEach
    public void personsShouldBeInListPersons() {
        System.out.println(filePersonRepository.getPersons());
    }

    @Test
    public void testFindById() throws PersonNotFoundException {
        Person person = filePersonRepository.findPersonById(1);
        System.out.println(person.getEmail());
        System.out.println(person.getPassword());
        System.out.println(person);
    }

    @Test
    public void throwsPersonNotFoundError() {
        assertThrows(PersonNotFoundException.class, () -> filePersonRepository.findPersonById(19));
    }

    @Test
    public void testFindPerson() throws PersonNotFoundException {
        Person person = filePersonRepository.findPerson("blemarcq@abis.be", "somepass4");
        System.out.println(person);
        assert person.equals(filePersonRepository.findPersonById(4));
    }

    @Test
    public void ex3a() {
        List<Person> personList = filePersonRepository.getPersons();
        List<Person> filteredPersonList = personList.stream()
                .filter(person -> !"null".equals(person.getCompany().getName()))
                .collect(Collectors.toList());
        System.out.println(filteredPersonList.size());
        System.out.println(filteredPersonList);
        System.out.println(personList.get(8).getCompany());
    }

    @Test
    public void ex3b() {
        List<Person> personList = filePersonRepository.getPersons();
        personList.stream()
                .filter(person -> person.getLastName().startsWith("S"))
                .sorted(Comparator.comparing(Person::getFirstName))
                .forEach(person -> System.out.println(person.getFirstName() + " " + person.getLastName().toUpperCase()));
    }

    @Test
    public void ex3c() {
        List<Person> personList = filePersonRepository.getPersons();
        personList.stream()
                .map(Person::getCompany)
                .distinct()
                .forEach(company -> System.out.println(company.getName()));
    }

    @Test
    public void ex3d() {
        List<Person> personList = filePersonRepository.getPersons();
        long counted = personList.stream()
                .filter(person -> "Leuven".equalsIgnoreCase(person.getCompany().getAddress().getTown()))
                .peek(System.out::println)
                .count();
        System.out.println(counted);
    }

    @Test
    public void ex3e() {
        List<Person> personList = filePersonRepository.getPersons();
        Optional found = personList.stream()
                .sorted(Comparator.comparing(Person::calculateAge))
                .findFirst();
        System.out.println(found);
    }

    @Test
    public void ex3f() {
        List<Person> personList = filePersonRepository.getPersons();
        Person found = personList.stream()
                .filter(person -> person.calculateAge()>50)
                .sorted(Comparator.comparing(Person::calculateAge))
                .findFirst().orElse(null);
        System.out.println(found);
    }

    @Test
    public void ex3g() {
        List<Person> personList = filePersonRepository.getPersons();
        Map<Company, List<Person>> mapPersonsByCompany = personList.stream()
                .collect(Collectors.groupingBy(Person::getCompany));
        System.out.println(mapPersonsByCompany);
    }

    @Test
    public void ex3h() {
        List<Person> personList = filePersonRepository.getPersons();
        Map<Company, Long> mapPersonsByCompany = personList.stream()
                .collect(Collectors.groupingBy(Person::getCompany, Collectors.counting()));
        System.out.println(mapPersonsByCompany);
    }

    @Test
    public void ex3i(){
        List<Person> personList = filePersonRepository.getPersons();
        Double averageAge = personList.stream()
                .map(Person::calculateAge)
                .collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(averageAge);
    }

    @Test
    public void ex4() {
        List<Person> personList = filePersonRepository.getPersons();
        personList.removeIf(person -> "null".equals(person.getCompany().getName()));
        System.out.println(personList);
    }

    @Test
    public void ex5() {
        List<Person> personList = filePersonRepository.getPersons();
        List<Person> personListFiltered = personList.stream()
                .filter(person -> "Belgium".equalsIgnoreCase(person.getCompany().getAddress().getCountry())
                    | "België".equalsIgnoreCase(person.getCompany().getAddress().getCountry())
                    | "Belgique".equalsIgnoreCase(person.getCompany().getAddress().getCountry())
                    && (person.calculateAge()>40))
                .filter(person -> (person.calculateAge()>40))
                .collect(Collectors.toList());
        System.out.println(personListFiltered);
        }

}