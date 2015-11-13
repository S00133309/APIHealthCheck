package HealthCheck.APIHealthCheck.dao;

import java.util.List;
import HealthCheck.APIHealthCheck.model.API;

public interface APIDAO {
	public int saveOrUpdate(API api);

	public int delete(int apiId);

	public API get(int apiId);

	public List<API> list();
}
