package HealthCheck.APIHealthCheck.dao;

import java.util.List;
import HealthCheck.APIHealthCheck.model.PersonalAPI;

public interface PersonalAPIDAO {
	public int saveOrUpdate(PersonalAPI personalAPI);
    
    public int delete(int personalApiId);
     
    public PersonalAPI get(int personalApiId);
     
    public List<PersonalAPI> list();
}
