package be.abis.exercise.model;
public class Company implements Comparable<Company> {
	
	private String name;
	private Address address;
	
	public Company(){}

	public Company(String name) {
		this();
		this.name=name;
	}
	
	public Company(String name, Address address) {
		this(name);
		this.address=address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return this.getName() +  ((address!=null)?" at: " + address.toString():"");
	}

	@Override
	public int compareTo(Company o) {
		return this.getName().compareTo(o.getName());
	}

	@Override
	public boolean equals(Object o){
		return this.getName().equals(((Company)o).getName());
	}

	@Override
	public int hashCode(){
		return name.length();
	}
}