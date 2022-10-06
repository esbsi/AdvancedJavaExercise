package be.abis.exercise.repository;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FilePersonRepository implements PersonRepository{

    private List<Person> persons = new ArrayList<>();
    private String fileLocation = "/temp/javacourses/persons.csv";

    public FilePersonRepository() {
        this.persons = getPersonsFromFile();

/*        this.persons.add(new Person("Jan", "Dhont"));
        this.persons.add(new Person("Jos", "Dhont"));
        this.persons.add(new Person("Jaap", "Dhont"));
        this.persons.add(new Person("Jim", "Dhont"));
        this.persons.add(new Person("Jana", "Dhont"));
        this.persons.add(new Person("Jietse", "Den Hondt"));
*/
    }

//    public StringBuilder personToStringBuilder(Person person){}

    public void writeAllPersonsToFile() throws IOException {
        try(PrintWriter writer = new PrintWriter(new FileWriter(fileLocation))){
            StringBuilder personAttributes = new StringBuilder();
            for (int i = 0; i<persons.size(); ++i){
                Person person = persons.get(i);
                personAttributes.append(i+1 + ";" + person.getFirstName() + ";" + person.getLastName() + ";" + "\n");
            }
            writer.append(personAttributes);
        }
    }

    public List<Person> getPersonsFromFile() throws RuntimeException {
        List<Person> personsFromFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("/temp/javacourses/persons.csv"))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] personAttributes = line.split(";");
                personsFromFile.add(new Person(
                        personAttributes[1],
                        personAttributes[2],
                        LocalDate.parse(personAttributes[3], DateTimeFormatter.ofPattern("d/M/yyyy")),
                        personAttributes[4],
                        personAttributes[5],
                        new Company(
                                personAttributes[6],
                                new Address(
                                        personAttributes[7],
                                        personAttributes[8],
                                        personAttributes[9],
                                        personAttributes[10],
                                        personAttributes[11],
                                        personAttributes[12]
                                )
                            )
                        ));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } return personsFromFile;
    }

    public Person findPersonByListIndex(int i){
        return persons.get(i);
    }



    // getset

    public List<Person> getPersons() {
        return persons;
    }


}
