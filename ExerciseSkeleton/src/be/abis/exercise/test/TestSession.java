package be.abis.exercise.test;

import be.abis.exercise.model.*;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class TestSession {

    PersonRepository personRepository = new FilePersonRepository();
    Instructor instructor = personRepository.findPersonByListIndex(0);
    PublicSession publicSession = new PublicSession(Course.JAVA_ADVANCED, LocalDate.now(), ((Person)instructor).getCompany(), instructor);
    String fileLocation = "/temp/javacourses/fancyFormattedCourse.txt";

    @Test
    public void printToFile() throws IOException {
        publicSession.addEnrolment(personRepository.findPersonByListIndex(1));
        publicSession.addEnrolment(personRepository.findPersonByListIndex(2));

        try(PrintWriter writer = new PrintWriter(new FileWriter(fileLocation))){
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append();


            writer.printf("%-20s%s", "", publicSession.getCourse().getTitle() + "\n----------------------------------------------------------------\n");
            writer.printf("%-20s%s", "Instructor:", publicSession.getInstructor() + "\n");
            writer.printf("%-20s%s", "Location:",
                    publicSession.getLocation().getName() + ", " +
                    publicSession.getLocation().getAddress().getStreet() + " " +
                    publicSession.getLocation().getAddress().getNr() + ", " +
                    publicSession.getLocation().getAddress().getZipCode() + " " +
                    publicSession.getLocation().getAddress().getTown() +
                    "\n----------------------------------------------------------------\n");



        }

    }

}
