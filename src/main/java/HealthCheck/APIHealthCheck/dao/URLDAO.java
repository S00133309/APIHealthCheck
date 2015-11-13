package HealthCheck.APIHealthCheck.dao;

import java.util.List;
import HealthCheck.APIHealthCheck.model.URL;

public interface URLDAO {
	public int saveOrUpdate(URL url);

	public int delete(int urlId);

	public URL get(int urlId);

	public List<URL> list();
}
