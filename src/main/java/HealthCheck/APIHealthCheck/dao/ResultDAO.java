package HealthCheck.APIHealthCheck.dao;

import java.util.List;
import HealthCheck.APIHealthCheck.model.Result;

public interface ResultDAO {
	public int saveOrUpdate(Result result);
    
    public int delete(int resultId);
     
    public Result get(int resultId);
     
    public List<Result> list();
}
