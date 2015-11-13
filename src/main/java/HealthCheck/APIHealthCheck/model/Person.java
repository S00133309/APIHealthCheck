package HealthCheck.APIHealthCheck.model;

public class Person {
	private int id;
	private String fname;
	private String sname;

	public Person() {
	}

	public Person(String fname, String sname) {
		this.fname = fname;
		this.sname = sname;
	}

	public int getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public String getSname() {
		return sname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
}
