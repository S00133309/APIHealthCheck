package HealthCheck.APIHealthCheck.dao;

import java.util.List;
import HealthCheck.APIHealthCheck.model.Person;

public interface PersonDAO {
	public int saveOrUpdate(Person person);
    
    public int delete(int personId);
     
    public Person get(int personId);
     
    public List<Person> list();
}
