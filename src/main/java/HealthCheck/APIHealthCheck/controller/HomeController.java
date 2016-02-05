package HealthCheck.APIHealthCheck.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import HealthCheck.APIHealthCheck.dao.APIResultDAO;
import HealthCheck.APIHealthCheck.dao.ResultDAO;
import HealthCheck.APIHealthCheck.dao.URLDAO;
import HealthCheck.APIHealthCheck.model.API;
import HealthCheck.APIHealthCheck.model.APIResult;
import HealthCheck.APIHealthCheck.model.GlobalViewData;
import HealthCheck.APIHealthCheck.model.Result;
import HealthCheck.APIHealthCheck.model.URL;
import HealthCheck.APIHealthCheck.service.Timing;

@RestController
public class HomeController {

	@Autowired
	private APIDAO apiDAO;

	@Autowired
	private URLDAO urlDAO;

	@Autowired
	private APIResultDAO apiResultDAO;
	
	@Autowired
	private ResultDAO resultDAO;
	
	@Autowired
	private Timing timing;

	@RequestMapping(value = "/")
	public ModelAndView globalList(ModelAndView model) throws IOException {
		List<API> listAPI = apiDAO.list();
		List<GlobalViewData> listGlobalData = new ArrayList<GlobalViewData>();
		GlobalViewData globalViewData;
		List<APIResult> apiResults = apiResultDAO.list();
		List<APIResult> apiResultsToGive;
		for (API api : listAPI) {
			apiResultsToGive = new ArrayList<APIResult>();
			for (APIResult apiResult : apiResults) {
				if(apiResult.getApiId() == api.getId())
					apiResultsToGive.add(apiResult);
			}
			globalViewData = new GlobalViewData(api, apiResultsToGive);
			listGlobalData.add(globalViewData);
		}
		model.addObject("data", listGlobalData);
		model.setViewName("globalApiList");
		return model;
	}
	
	@RequestMapping(value = "/details/{id}")
	public ModelAndView apiDetails(@PathVariable("id") int api_id, ModelAndView model) throws IOException {
		API api = apiDAO.get(api_id);
		List<URL> urls = urlDAO.listByApi(api.getId());
		List<Result> results = resultDAO.list();
		List<Result> resultsOfAPI = new ArrayList<Result>();
		for (URL url : urls) {
			for (Result result : results) {
				if(result.getUrlId() == url.getId())
					resultsOfAPI.add(result);
			}
		}
		model.addObject("api",api);
		model.addObject("urls",urls);
		model.addObject("results", resultsOfAPI);
		model.setViewName("apiDetails");
		return model;
	}

	@RequestMapping(value = "/api")
	public ModelAndView listAPI(ModelAndView model) throws IOException {
		List<API> listAPI = apiDAO.list();
		List<URL> listURL = urlDAO.list();
		List<APIResult> apiResults = apiResultDAO.list();
		model.addObject("listAPI", listAPI);
		model.addObject("listURL", listURL);
		model.addObject("listResults", apiResults);
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
