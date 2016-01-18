package HealthCheck.APIHealthCheck.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import HealthCheck.APIHealthCheck.dao.ResultDAO;
import HealthCheck.APIHealthCheck.dao.URLDAO;
import HealthCheck.APIHealthCheck.model.API;
import HealthCheck.APIHealthCheck.model.Result;
import HealthCheck.APIHealthCheck.model.URL;

public class Ping {

	@Autowired
	private URLDAO urlDAO;
	@Autowired
	private ResultDAO resultDAO;

	public Ping() {
	}

	public List<API> getNeededApis(List<API> apisUnchecked) {
		LocalDateTime now = LocalDateTime.now();
		List<API> apisChecked = new ArrayList<API>();
		int time = 0;
		for (API api : apisUnchecked) {
			time = api.getTime();
			if (time == now.getHour()) {
				apisChecked.add(api);
			}
		}
		return apisChecked;
	}

	public void pingApi(API api) {
		List<URL> urls = urlDAO.listByApi(api.getId());
		Calendar dateTime = Calendar.getInstance();
		for (URL url : urls) {
			try {
				java.net.URL connectionUrl = new java.net.URL("api Url Here");
				HttpURLConnection connection = (HttpURLConnection) connectionUrl.openConnection();
				connection.connect();
				Result result = new Result(url.getId(), connection.getResponseCode(),
						new Time(dateTime.getTimeInMillis()), new Date(dateTime.getTimeInMillis()), "");
				resultDAO.saveOrUpdate(result);
			} catch (MalformedURLException e) {
				Result result = new Result(url.getId(), 0, null, null, "Ping Failed due to bad URL.");
				resultDAO.saveOrUpdate(result);
			} catch (IOException e) {
				Result result = new Result(url.getId(), 0, null, null, "Ping Failed.");
				resultDAO.saveOrUpdate(result);

			}
		}
	}

}
