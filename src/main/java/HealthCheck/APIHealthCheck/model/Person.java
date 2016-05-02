package HealthCheck.APIHealthCheck.model;

public class Person {
	private int id;
	private String email;
	private String password;
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

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
}
