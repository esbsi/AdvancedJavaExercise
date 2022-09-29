package be.abis.exercise.model;

import java.time.LocalDate;
import java.util.Comparator;

public class Person implements Instructor, CourseParticipant, Comparable<CourseParticipant> {

	private static int counter = 0;

	private int personNumber;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	private String password;
	private Company company;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		personNumber = ++counter;
	}

	public Person(String firstName, String lastName, Company company) {
		this(firstName, lastName);
		this.company = company;
	}
	
	public Person(String firstName, String lastName, LocalDate birthDate, String email,
			String password, Company company) {
		this(firstName,lastName,company);
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
	}

	public Person(String firstName, String lastName, LocalDate birthDate, String email,
			String password) {
		this(firstName,lastName);
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
	}

	public int getPersonNumber() {
		return personNumber;
	}
	
	public void setPersonNumber(int personNumber) {
		this.personNumber=personNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		firstName = fName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		lastName = lName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static int getNumberOfPersons() {
		return counter;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}
	


	public void teach(Course course) {
		System.out.println(this + " teaches " + course.getTitle());
	}

	public void attendCourse(Course course) {
		System.out.println(this + " follows " + course.getTitle());
	}
	
	@Override
	public int compareTo(CourseParticipant o) {
		return this.lastName.compareTo(((Person)o).lastName);
	}

    public static class FirstNameComparator implements Comparator<CourseParticipant>{
		@Override
		public int compare(CourseParticipant o1, CourseParticipant o2) {
			return ((Person)o1).getFirstName().compareToIgnoreCase(((Person)o2).getFirstName());
		}
    	 
    }
    
}