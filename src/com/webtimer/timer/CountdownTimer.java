package com.webtimer.timer;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {

	private Timer timer;

	private int delay = 0;
	private int updateInterval = 1000;

	int interval = 60000; //the countdown interval - default: 60 seconds
	static int countdown = 60000; //the time left over in the current interval
	boolean isRunning = false;

	boolean start(){
		timer = new Timer();
		timer.scheduleAtFixedRate(
				new TimerTask(){
					@Override
					public void run(){
						timeStep();
						System.out.println("Timer is at: " + countdown);
					}
				},
				this.delay,
				this.updateInterval
				);
		isRunning=true;
		return isRunning;
	}

	boolean stop(){
		timer.cancel();
		isRunning=false;
		return isRunning;
	}

	private void timeStep(){
		/*
		 * Helper method to update the time left over in the current run.
		 * Reset interval when it runs out.
		 */
		if (countdown <= 0 || countdown > interval){	
			countdown = interval;
		}
		countdown -= updateInterval;
	}

	public void setNewTime(int newtime) {
		interval = newtime;
	}
}
