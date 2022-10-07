package be.abis.exercise.model;

import be.abis.exercise.exception.InvoiceException;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class Session implements Service {
	private Course course;
	private LocalDate date;
	private Company location;
	private Instructor instructor;

	public Session(Course course, LocalDate date, Company location,
			Instructor instructor) {
		this.course=course;
		this.date=date;
		this.location=location;
		this.instructor=instructor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Company getLocation() {
		return location;
	}

	public void setLocation(Company location) {
		this.location = location;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public abstract Company getOrganizer();

	public abstract double invoice() throws InvoiceException;


	@Override
	public String toString() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		return "The session about " + this.getCourse().getTitle()
				+ " will be given at " + this.getLocation().getName()
				+ " by " + this.getInstructor().toString() + " on " + fmt.format(this.getDate()) + ".";
	}

	public String toString(String localeName) {
		Locale dk = new Locale(localeName);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMMM yyyy", dk);
//		String baseName = "C:\\Users\\Duser\\IdeaProjects\\advancedjava\\ExerciseSkeleton\\src\\be\\abis\\i18n\\resources\\applicationResources";
		String baseName = "be.abis.i18n.resources.applicationResources";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, dk);
		return bundle.getString("theSessionAbout") + this.getCourse().getTitle() + " "
				+ bundle.getString("willBeGivenAt") + this.getLocation().getName() + " "
				+ MessageFormat.format(bundle.getString("byXonY"),  this.getInstructor().toString(), fmt.format(this.getDate()));
	}


	/*
			locale = Locale.getDefault();
		Locale.setDefault(new Locale("dk"));
	 */
}