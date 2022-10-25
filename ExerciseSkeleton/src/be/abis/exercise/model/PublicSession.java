package be.abis.exercise.model;


import be.abis.exercise.exception.InvoiceException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PublicSession extends Session {
	
	public final static Company ABIS = new Company("Abis");
	private ArrayList<CourseParticipant> enrolments = new ArrayList<>();

	public PublicSession(Course course, LocalDate date, Company location,
			Instructor instructor) {
		super(course, date, location, instructor);
	}

	@Override
	public Company getOrganizer() {
		return ABIS;
	}

	public ArrayList<CourseParticipant> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(ArrayList<CourseParticipant> enrolments) {
		this.enrolments = enrolments;
	}

	@Override
	public double invoice() throws InvoiceException {
		System.out.println("Invoice in PublicSession");
		return 500;
	}

	public void addEnrolment(CourseParticipant... cps) {
		for (CourseParticipant c : cps)
			this.addEnrolment(c);
	}

	protected void addEnrolment(CourseParticipant cp) {
		if (!enrolments.contains(cp)) {
			enrolments.add(cp);
			System.out.println("Enrolment added to the list, now "
					+ enrolments.size() + " enrolments.");
			System.out.println("enrollee is: " + cp);
		} else {
			System.out.println("Couldn't add " + cp + " as enrollee, since he was already enrolled");
		}
	}

	public void removeEnrolment(CourseParticipant... cps) {
		for (CourseParticipant c : cps)
			removeEnrolment(c);
	}

	protected void removeEnrolment(CourseParticipant cp) {
		if (enrolments.contains(cp)) {
			enrolments.remove(cp);
			System.out.println("Enrollee " + cp + " removed from the list, now "
					+ enrolments.size() + " enrolments.");
		} else {
			System.out.println("Couldn't remove enrolment.");
		}
	}
	
	public Iterator<CourseParticipant> getEnrolmentsIterator(){
		return enrolments.iterator();
	}
	
	public BigDecimal calculateSessionRevenue(){
		BigDecimal dailyPriceBD = new BigDecimal(((Double)this.getCourse().getDailyPrice()).toString());
		BigDecimal daysBD = new BigDecimal(((Integer)(this.getCourse().getDays())).toString());
		BigDecimal enrolmentsBD = new BigDecimal(((Integer)enrolments.size()).toString());
		BigDecimal fees = enrolmentsBD.multiply(dailyPriceBD.multiply(daysBD));
		BigDecimal tax = new BigDecimal("0.21");
		return fees.multiply(BigDecimal.ONE.subtract(tax));
	}

	public void printSessionRevenue(){
		BigDecimal bd = calculateSessionRevenue();
		double sessionRevenue = bd.doubleValue();
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
		decimalFormatSymbols.setDecimalSeparator(',');
		DecimalFormat decimalFormat = new DecimalFormat("€#0.00", decimalFormatSymbols);
		System.out.println(decimalFormat.format(sessionRevenue));
//		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("nl", "BE"));
//		System.out.println(numberFormat.format(sessionRevenue));
	}

	public void printParticipants(){
		for (CourseParticipant courseParticipant : enrolments){
			System.out.println(courseParticipant + ". Company: " +((Person)courseParticipant).getCompany().getName());
		}
	}

	public List<CourseParticipant> findAbisParticipants(){
		return enrolments.stream()
				.filter(courseParticipant -> "Abis".equalsIgnoreCase(((Person)courseParticipant).getCompany().getName()))
				.collect(Collectors.toList());
	}

	public void removeAbisParticipants(){
		Iterator<CourseParticipant> iterator = enrolments.iterator();
		while (iterator.hasNext()){
//			CourseParticipant courseParticipant = iterator.next();
			if ("Abis".equalsIgnoreCase((((Person)iterator.next()).getCompany()).getName())){
				iterator.remove();
			}
		}
	}

//	public void removeAbisParticipants(){enrolments.removeIf(courseParticipant -> "Abis".equalsIgnoreCase((((Person) courseParticipant).getCompany()).getName()));}

}