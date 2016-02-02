package HealthCheck.APIHealthCheck.model;

public class API {
	private int id;
	private String name;
	private int time;
	private String currentStatus;

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

	public String getCurrentStatus() {
		return currentStatus;
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

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
}
