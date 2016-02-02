package HealthCheck.APIHealthCheck.dao;

import java.util.List;

import HealthCheck.APIHealthCheck.model.APIResult;

public interface APIResultDAO {
	public int saveOrUpdate(APIResult apiResult);

	public int delete(int apiResultId);

	public APIResult get(int apiResultId);

	public List<APIResult> list();
}
