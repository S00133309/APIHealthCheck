package HealthCheck.APIHealthCheck.dao;

import java.util.List;

import HealthCheck.APIHealthCheck.model.Contact;

public interface ContactDAO {
	public int saveOrUpdate(Contact contact);
    
    public int delete(int contactId);
     
    public Contact get(int contactId);
     
    public List<Contact> list();
}
