package HealthCheck.APIHealthCheck.model;

public class URL {
	private int id;
	private int api_id;
	private String url;

	public URL() {
	}

	public URL(int api_id, String url) {
		this.url = url;
		this.api_id = api_id;
	}

	public int getId() {
		return id;
	}

	public int getApiId() {
		return api_id;
	}

	public String getUrl() {
		return url;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setApiId(int api_id) {
		this.api_id = api_id;
	}
}
