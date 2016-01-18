package HealthCheck.APIHealthCheck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import HealthCheck.APIHealthCheck.dao.APIDAO;

import HealthCheck.APIHealthCheck.model.API;

public class Timing {
	@Autowired
	private APIDAO apiDAO;

	@Autowired
	private Ping ping;

	private int count = 0;
	private List<API> apis = new ArrayList<API>();
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public Timing() {
		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				apis = apiDAO.list();
				apis = ping.getNeededApis(apis);
				for (API api : apis) {
					count++;
					ping.pingApi(api);
				}
				count++;
			}
		}, 1, 5, TimeUnit.SECONDS);
	}

	public int getList() {
		return apis.size();
	}
	
	public int getCount(){
		return count;
	}
}
