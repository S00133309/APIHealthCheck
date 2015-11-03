package HealthCheck.APIHealthCheck.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import HealthCheck.APIHealthCheck.dao.ContactDAO;
import HealthCheck.APIHealthCheck.model.Contact;

@RestController
public class HomeController {

	@Autowired
	private ContactDAO contactDAO;

	@RequestMapping(value = "/")
	public ModelAndView listContact(ModelAndView model) throws IOException {
		List<Contact> listContact = contactDAO.list();
		model.addObject("listContact", listContact);
		model.setViewName("index");

		return model;
	}

	@RequestMapping(value = "/deleteContact/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteContact(@PathVariable("id") int contact_id) {
		int rowsAffected = contactDAO.delete(contact_id);
		if(rowsAffected == 1)
			return contact_id;
		else
			return -1;
	}

	@RequestMapping(value = "/editContact", method = RequestMethod.PUT)
	public Contact editContact(@RequestBody Contact contact) {
		int rowsAffected = contactDAO.saveOrUpdate(contact);
		if (rowsAffected == 1)
			return contact;
		else {
			return null;
		}
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public Contact getSearchResultViaAjax(@RequestBody Contact contact) {
		int rowsAffected = contactDAO.saveOrUpdate(contact);
		if (rowsAffected == 1)
			return contact;
		else {
			return null;
		}

	}
}
