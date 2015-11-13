package HealthCheck.APIHealthCheck.model;

public class URL {
	private int id;
	private int apiId;
	private String url;

	public URL() {
	}

	public URL(int apiId, String url) {
		this.url = url;
		this.apiId = apiId;
	}

	public int getId() {
		return id;
	}

	public int getApiId() {
		return apiId;
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

	public void setApiId(int apiId) {
		this.apiId = apiId;
	}
}
