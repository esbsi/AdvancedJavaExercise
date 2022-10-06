package be.abis.exercise.test;

import be.abis.exercise.exception.ZipCodeNotCorrectException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Person;
import org.junit.jupiter.api.Test;

public class TestAddressCheckZipCode {

    @Test
    public void zipCheckShouldBeTrueBE() throws ZipCodeNotCorrectException {
        Address addressBE = new Address("Beekstraat", "12", "9000", "Gent", "Belgium", "BE");
        assert addressBE.checkZipCode();
    }

    @Test
    public void zipCheckShouldBeTrueNL() throws ZipCodeNotCorrectException {
    Address addressNL = new Address("Beekstraat", "12", "9000 NL", "Amstelveen", "Belgium", "NL");
    assert addressNL.checkZipCode();



    }

}
