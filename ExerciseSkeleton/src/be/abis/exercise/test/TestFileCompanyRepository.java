package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FileCompanyRepository;
import be.abis.exercise.repository.FilePersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestFileCompanyRepository {

    Logger logger = LogManager.getLogger();
    Logger exceptionLogger = LogManager.getLogger("exceptionLogger");


    FilePersonRepository filePersonRepository = new FilePersonRepository();

    @BeforeEach
    public void personsShouldBeInListPersons(){
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
    public void throwsPersonNotFoundError(){assertThrows(PersonNotFoundException.class, () -> filePersonRepository.findPersonById(19));
    }

    @Test
    public void testFindPerson() throws PersonNotFoundException {
        Person person = filePersonRepository.findPerson("blemarcq@abis.be", "somepass4");
        System.out.println(person);
        assert person.equals(filePersonRepository.findPersonById(4));
    }

    @Test
    public void ex3a(){
        List<Person> personList = filePersonRepository.getPersons();
        List<Person> filteredPersonList = personList.stream()
                                                    .filter(person -> !"null".equals(person.getCompany().getName()))
                                                    .collect(Collectors.toList());
        System.out.println(filteredPersonList.size());
        System.out.println(filteredPersonList);
        System.out.println(personList.get(8).getCompany());
    }

    @Test
    public void ex3b(){
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

 /*   @Test
    public void ex3c() {
        List<Person> personList = filePersonRepository.getPersons();
   */



/*    @Test
    public void fileShouldExist() throws IOException {
        filePersonRepository.writeAllPersonsToFile();
    }
*/
}
