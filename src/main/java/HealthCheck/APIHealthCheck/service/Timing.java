package HealthCheck.APIHealthCheck.service;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import HealthCheck.APIHealthCheck.model.API;

public class Timing {

	private Timer timer = new Timer();
	private Ping ping = new Ping();
	private TimerTask task = new TimerTask() {

		@Override
		public void run() {
			List<API> apis = ping.getNeededApis();
			for (API api : apis) {
				ping.pingApi(api);
			}
		}
	};

	public Timing() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
		calendar.set(Calendar.MINUTE, 0);
		//calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1); FOR TESTING ONLY
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long hourInMilliseconds = 3600000;
		timer.scheduleAtFixedRate(task, calendar.getTime(), hourInMilliseconds);
	}

}
