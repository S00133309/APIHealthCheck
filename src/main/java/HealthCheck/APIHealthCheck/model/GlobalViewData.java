package HealthCheck.APIHealthCheck.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GlobalViewData {
	private int id;
	private String name;
	private String currentStatus;
	private List<String> lastTenResultStatus = new ArrayList<String>();
	private List<Date> lastTenDates = new ArrayList<Date>();
	private int totalUp;
	private int totalDown;
	private int totalUnstable;

	public GlobalViewData(API api, List<APIResult> apiResults) {
		id = api.getId();
		name = api.getName();
		currentStatus = api.getCurrentStatus();
		calculateTopTen(api, apiResults);
	}

	private void calculateTopTen(API api, List<APIResult> apiResults) {
		int count = 0;
		for (APIResult apiResult : apiResults) {
			if (count < 10) {
				lastTenResultStatus.add(apiResult.getStatus());
				lastTenDates.add(apiResult.getDate());
			}
			if (apiResult.getStatus().equals("Healthy"))
				totalUp++;
			else if (apiResult.getStatus().equals("Unstable"))
				totalUnstable++;
			else
				totalDown++;
			count++;

		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public List<String> getLastTenResultStatus() {
		return lastTenResultStatus;
	}

	public List<Date> getLastTenDates() {
		return lastTenDates;
	}

	public int getTotalUp() {
		return totalUp;
	}

	public int getTotalDown() {
		return totalDown;
	}

	public int getTotalUnstable() {
		return totalUnstable;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setLastTenResultStatus(List<String> lastTenResultStatus) {
		this.lastTenResultStatus = lastTenResultStatus;
	}

	public void setLastTenDates(List<Date> lastTenDates) {
		this.lastTenDates = lastTenDates;
	}

	public void setTotalUp(int totalUp) {
		this.totalUp = totalUp;
	}

	public void setTotalDown(int totalDown) {
		this.totalDown = totalDown;
	}

	public void setTotalUnstable(int totalUnstable) {
		this.totalUnstable = totalUnstable;
	}
}
