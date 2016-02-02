package HealthCheck.APIHealthCheck.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import HealthCheck.APIHealthCheck.dao.APIDAO;
import HealthCheck.APIHealthCheck.dao.APIResultDAO;
import HealthCheck.APIHealthCheck.dao.ResultDAO;
import HealthCheck.APIHealthCheck.model.API;
import HealthCheck.APIHealthCheck.model.APIResult;
import HealthCheck.APIHealthCheck.model.Result;

public class Timing {
	@Autowired
	private APIDAO apiDAO;

	@Autowired
	private Ping ping;

	@Autowired
	private ResultDAO resultDAO;

	@Autowired
	private APIResultDAO apiResultDAO;

	private APIResult apiResult = new APIResult();
	private List<Result> results = new ArrayList<Result>();
	private List<API> apis = new ArrayList<API>();
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public Timing() {
		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {

				apis = apiDAO.list();

				apis = ping.getNeededApis(apis);
				for (API api : apis) {
					results = ping.pingApi(api);
					int goodResults = 0;
					for (Result result : results) {
						if (result.getResponseCode() == 200)
							goodResults++;
						resultDAO.saveOrUpdate(result);
					}
					if (goodResults == results.size())
						api.setCurrentStatus("Healthy");
					else if (goodResults > 0)
						api.setCurrentStatus("Unstable");
					else
						api.setCurrentStatus("Down");
					apiDAO.saveOrUpdate(api);

					apiResult = new APIResult();
					apiResult.setApiId(api.getId());
					apiResult.setStatus(api.getCurrentStatus());
					apiResult.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
					apiResultDAO.saveOrUpdate(apiResult);
				}
			}
		}, 1, 30, TimeUnit.MINUTES);

	}
}
