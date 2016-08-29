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
	
	/*
	 * Default values to change the interval with.
	 */
	//Todo: move these to a conf file.
	private static int plusTime = 10000;
	private static int minusTime = 10000;
	
	/*
	 * The interval must be between <lowerLimit> and <higherLimit>
	 */
	private static int lowerLimit = 20000;
	private static int higherLimit = 120000;
	
	//////////////////////////////////////////////////////////////////////////////////
	
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
	
	////////////////////////////////////////////////////////////////////////////////
	
	/*METHODS*/
	
	/*
	 * Start the timer and keep it going.
	 */
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

	/*
	 * Stop the timer.
	 */
	boolean stop(){
		timer.cancel();
		isRunning=false;
		return isRunning;
	}

	/*
	 * Helper method to update the time left over in the current run.
	 * Reset interval when it runs out.
	 */
	private void timeStep(){
		if (countdown <= 0){
			countdown = interval;
		}
		countdown -= updateInterval;
	}
	
	/*
	 * Add a bit of extra time to the next timer.
	 * Must be between the two limits.
	 */
	static void doPlus(){
		if (interval >= CountdownTimer.lowerLimit && interval < CountdownTimer.higherLimit){
		interval += CountdownTimer.plusTime;
		}
	}
	
	/*
	 * Subtract a bit of extra time from the next timer.
	 * Must be between the two limits.
	 */
	static void doMinus(){
		if (interval > CountdownTimer.lowerLimit && interval <= CountdownTimer.higherLimit){
			interval -= minusTime;
		}
	}
}
