package HealthCheck.APIHealthCheck.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import HealthCheck.APIHealthCheck.dao.APIDAO;
import HealthCheck.APIHealthCheck.dao.URLDAO;
import HealthCheck.APIHealthCheck.model.API;

public class Ping {
	@Autowired
	private APIDAO apiDAO;

	@Autowired
	private URLDAO urlDAO;

	public Ping() {
	}

	public List<API> getNeededApis() {
		List<API> apisUnchecked = apiDAO.list();
		List<API> apisChecked = new ArrayList<API>();

		for (API api : apisUnchecked) {
			// If Api hour is now, add it to the return list.
		}

		return apisChecked;
	}

	public void pingApi(API api) {
		// Get all the urls for that api and then ping them
		List<URL> urls = new ArrayList<URL>();

		for (URL url : urls) {
			try {
				URL connectionUrl = new URL("api Url Here");// Note: This URL is
															// a java.net url
															// object
				HttpURLConnection connection = (HttpURLConnection) connectionUrl.openConnection();
				connection.connect();

				// Get all the stuff we want here, eg. response code.
				int code = connection.getResponseCode();
				// Then store it in a result object and save that to db

			} catch (MalformedURLException e) {
				// Log this as a failure because of bad url
			} catch (IOException e) {
				// Log this as a failure because of reasons
			}
			// Get all the stuff we want here, eg. response code.

		}
	}
}
