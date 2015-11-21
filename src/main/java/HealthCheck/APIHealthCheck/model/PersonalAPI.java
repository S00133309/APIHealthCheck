package HealthCheck.APIHealthCheck.model;

public class PersonalAPI {
	private int id;
	private int personId;
	private int apiId;

	public PersonalAPI() {
	}

	public PersonalAPI(int personId, int apiId) {
		this.personId = personId;
		this.apiId = apiId;
	}

	public int getId() {
		return id;
	}

	public int getPersonId() {
		return personId;
	}

	public int getApiId() {
		return apiId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public void setApiId(int apiId) {
		this.apiId = apiId;
	}
}
