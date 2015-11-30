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

import HealthCheck.APIHealthCheck.dao.APIDAO;
import HealthCheck.APIHealthCheck.dao.URLDAO;
import HealthCheck.APIHealthCheck.model.API;
import HealthCheck.APIHealthCheck.model.URL;
import HealthCheck.APIHealthCheck.service.Timing;

@RestController
public class HomeController {

	@Autowired
	private APIDAO apiDAO;

	@Autowired
	private URLDAO urlDAO;

	@Autowired
	private Timing timing;

	@RequestMapping(value = "/")
	public ModelAndView listContact(ModelAndView model) throws IOException {
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = "/api")
	public ModelAndView listAPI(ModelAndView model) throws IOException {
		List<API> listAPI = apiDAO.list();
		List<URL> listURL = urlDAO.list();
		model.addObject("listAPI", listAPI);
		model.addObject("listURL", listURL);
		model.setViewName("api");

		return model;
	}

	@RequestMapping(value = "/deleteApi/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteApi(@PathVariable("id") int api_id) {
		int rowsAffected = apiDAO.delete(api_id);
		if (rowsAffected == 1)
			return api_id;
		else
			return -1;
	}

	@RequestMapping(value = "/editApi", method = RequestMethod.PUT)
	public API editApi(@RequestBody API api) {
		int rowsAffected = apiDAO.saveOrUpdate(api);
		if (rowsAffected == 1)
			return api;
		else {
			return null;
		}
	}

	@RequestMapping(value = "/saveApi", method = RequestMethod.POST)
	public API saveApi(@RequestBody API api) {
		int rowsAffected = apiDAO.saveOrUpdate(api);
		if (rowsAffected == 1)
			return api;
		else {
			return null;
		}

	}
}
