package be.abis.exercise.test;

import be.abis.exercise.repository.FileCompanyRepository;
import be.abis.exercise.repository.FilePersonRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestFileCompanyRepository {

    FilePersonRepository filePersonRepository = new FilePersonRepository();

    @Test
    public void personsShouldBeInListPersons(){
        System.out.println(filePersonRepository.getPersons());
    }

/*    @Test
    public void fileShouldExist() throws IOException {
        filePersonRepository.writeAllPersonsToFile();
    }
*/
}
