package HealthCheck.APIHealthCheck.model;

import java.sql.Date;

public class APIResult {
	private int id;
	private int apiId;
	private Date date;
	private String status;
	private int paId;

	public APIResult() {
	}

	public int getId() {
		return id;
	}

	public int getApiId() {
		return apiId;
	}

	public Date getDate() {
		return date;
	}

	public String getStatus() {
		return status;
	}
	
	public int getPaId() {
		return paId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setApiId(int apiId) {
		this.apiId = apiId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setPaId(int paId) {
		this.paId = paId;
	}
}
