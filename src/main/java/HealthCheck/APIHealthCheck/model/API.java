package HealthCheck.APIHealthCheck.model;

public class API {
	private int id;
	private String name;
	private int time;

	public API() {
	}

	public API(String name, int time) {
		this.name = name;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getTime() {
		return time;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
