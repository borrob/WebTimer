package com.webtimer.timer;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {

	private Timer timer;

	private int delay = 0;
	private int updateInterval = 1000;

	static String timers = "10,7,8"; //starting list of count down times [s]
	private int interval = 60000; //the countdown interval - default: 60 seconds
	static int countdown = 3000; //the time left over in the current interval
	boolean isRunning = false;

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
			//TODO: perhaps makes this an array or list or something: a bit ugly to work with this string
			int kommaPlace = timers.indexOf(",");
			if (kommaPlace > 0){
				interval = Integer.parseInt(timers.substring(0, kommaPlace)) * 1000;
				timers = timers.substring(kommaPlace+1);
			} else {
				interval = Integer.parseInt(timers) * 1000;
			}
			countdown = interval;
		}
		countdown -= updateInterval;
	}
	
	public void addToTimerString(int time){
		timers += "," + String.valueOf(time);
	}
}
