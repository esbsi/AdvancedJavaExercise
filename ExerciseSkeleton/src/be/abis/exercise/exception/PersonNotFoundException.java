package be.abis.exercise.exception;

import java.util.function.Supplier;

public class PersonNotFoundException extends Exception {

	public PersonNotFoundException(String message) {
		super(message);
	}


}
