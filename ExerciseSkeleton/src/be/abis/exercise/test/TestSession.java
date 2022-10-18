package be.abis.exercise.test;

import be.abis.exercise.model.*;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestSession {

    PersonRepository personRepository = new FilePersonRepository();
    Instructor instructor = personRepository.findPersonByListIndex(0);
    PublicSession publicSession = new PublicSession(Course.JAVA_ADVANCED, LocalDate.now(), ((Person)instructor).getCompany(), instructor);
    String fileLocation = "/temp/javacourses/fancyFormattedCourse.txt";

    @Test
    public void printToFile() throws IOException {
        publicSession.addEnrolment(personRepository.findPersonByListIndex(1));
        publicSession.addEnrolment(personRepository.findPersonByListIndex(4));
        publicSession.addEnrolment(personRepository.findPersonByListIndex(2));
        try(PrintWriter writer = new PrintWriter(new FileWriter(fileLocation))){
            int lineLength = 80;
            String horizontalLine = new String(new char[lineLength]).replace("\0", "-");
            String courseTitle = publicSession.getCourse().getTitle();
            String regexTitle = "%-" + (lineLength/2 - courseTitle.length()/2) + "s%s";
            writer.printf(regexTitle, "", courseTitle + "\n" + horizontalLine + "\n"); // Center title
            writer.printf("%-20s%s", "Instructor:", publicSession.getInstructor() + "\n");
            writer.printf("%-20s%s", "Location:",
                    publicSession.getLocation().getName() + ", " +
                    publicSession.getLocation().getAddress().getStreet() + " " +
                    publicSession.getLocation().getAddress().getNr() + ", " +
                    publicSession.getLocation().getAddress().getZipCode() + " " +
                    publicSession.getLocation().getAddress().getTown() + "\n" + horizontalLine + "\n"
                    );
            DecimalFormat decimalFormat = new DecimalFormat("00");
            List<Person> participantsSortedByCompany = publicSession.getEnrolments().stream()
                                                                        .map(a -> (Person)a)
                                                                        .sorted(Comparator.comparing(a -> a.getCompany().getName()))
                                                                        .collect(Collectors.toList());
            int i = 0;
            for (Person person : participantsSortedByCompany) {
                i++;
                writer.printf("%-6s%-35s%s", decimalFormat.format(i), person.getCompany().getName(),
                        person.getFirstName() + " " + person.getLastName().toUpperCase() + "\n");
            } writer.printf(horizontalLine);
        }
    }

    @Test
    public void printRevenue(){
        publicSession.addEnrolment(personRepository.findPersonByListIndex(1));
        publicSession.addEnrolment(personRepository.findPersonByListIndex(2));
        publicSession.printSessionRevenue();
    }

    @Test
    public void printToStringDk(){
        System.out.println(publicSession.toString("dk"));
    }

}
