package com.webtimer.timer;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {

	private Timer timer;

	private int delay = 0;
	private int updateInterval = 1000;

	private static int interval = 90000; //the countdown interval - default: 90 seconds
	private static int countdown = 3000; //the time left over in the current interval
	boolean isRunning = false;
	
	/* GETTER AND SETTERS */
	
	public static int getInterval() {
		return interval;
	}

	public static void setInterval(int interval) {
		CountdownTimer.interval = interval;
	}

	public static int getCountdown() {
		return countdown;
	}

	public static void setCountdown(int countdown) {
		CountdownTimer.countdown = countdown;
	}
	
	/*METHODS*/
	boolean start(){
		timer = new Timer();
		timer.scheduleAtFixedRate(
				new TimerTask(){
					@Override
					public void run(){
						timeStep();
						System.out.println("Timer is at: " + String.valueOf(countdown));
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
		if (countdown <= 0){
			countdown = interval;
		}
		countdown -= updateInterval;
	}
}
