package HealthCheck.APIHealthCheck.model;

import java.sql.Date;
import java.sql.Time;

public class Result {
	private int id;
	private int urlId;
	private int responseCode;
	private Time timePinged;
	private Date datePinged;
	private String note;

	public Result() {
	}

	public Result(int urlId, int responseCode, Time timePinged, Date datePinged, String note) {
		this.urlId = urlId;
		this.responseCode = responseCode;
		this.timePinged = timePinged;
		this.datePinged = datePinged;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public int getUrlId() {
		return urlId;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public Time getTimePinged() {
		return timePinged;
	}

	public Date getDatePinged() {
		return datePinged;
	}

	public String getNote() {
		return note;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public void setTimePinged(Time timePinged) {
		this.timePinged = timePinged;
	}

	public void setDatePinged(Date datePinged) {
		this.datePinged = datePinged;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
