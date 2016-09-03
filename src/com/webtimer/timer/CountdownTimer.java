package com.webtimer.timer;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {

	private Timer timer;

	private final int DELAY = 0;
	private final int UPDATE_INTERVAL = 1000;

	private final int DEFAULT_INTERVAL = 90000; //the countdown interval - default: 90 seconds
	
	private static int interval;
	private static int interval2;
	private static int countdown = 3000; //the time left over in the current interval
	private static String comments = "";
	boolean isRunning = false;
	
	/**
	 * Default values to change the interval with.
	 */
	//Todo: move these to a conf file.
	private static int plusTime = 10000;
	private static int minusTime = 10000;
	
	/**
	 * The interval must be between <lowerLimit> and <higherLimit>
	 */
	private static int lowerLimit = 20000;
	private static int higherLimit = 120000;
	
	private static int maxLinesUserComments = 5;

	//////////////////////////////////////////////////////////////////////////////////
	
	/* GETTER AND SETTERS */
	
	public static int getInterval() {
		return interval;
	}

	public static void setInterval(int interval) {
		CountdownTimer.interval = interval;
	}
	
	public static int getInterval2() {
		return interval2;
	}

	public static void setInterval2(int interval) {
		CountdownTimer.interval2 = interval;
	}

	public static int getCountdown() {
		return countdown;
	}

	public static void setCountdown(int countdown) {
		CountdownTimer.countdown = countdown;
	}
	
	public static String getComments(){
		return comments;
	}
	
	public static void setComments(String c){
		CountdownTimer.comments = c;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	/*METHODS*/
	
	/**
	 * Start the timer and keep it going.
	 */
	public boolean start(){
		interval = this.DEFAULT_INTERVAL;
		interval2 = this.DEFAULT_INTERVAL;
		
		timer = new Timer();
		timer.scheduleAtFixedRate(
				new TimerTask(){
					@Override
					public void run(){
						timeStep();
						System.out.println("Timer is at: " + String.valueOf(countdown));
					}
				},
				this.DELAY,
				this.UPDATE_INTERVAL
				);
		isRunning=true;
		return isRunning;
	}

	/**
	 * Stop the timer.
	 */
	public boolean stop(){
		timer.cancel();
		isRunning=false;
		return isRunning;
	}

	/**
	 * Helper method to update the time left over in the current run.
	 * Reset interval when it runs out.
	 */
	private void timeStep(){
		if (countdown <= 0){
			countdown = interval;
			interval = interval2;
			interval2 = this.DEFAULT_INTERVAL;
		}
		countdown -= UPDATE_INTERVAL;
	}
	
	/**
	 * Add a bit of extra time to the next timer.
	 * Must be between the two limits.
	 */
	public static void doPlus(){
		if (interval >= lowerLimit && interval < higherLimit){
		interval += plusTime;
		}
	}
	
	/**
	 * Subtract a bit of extra time from the next timer.
	 * Must be between the two limits.
	 */
	public static void doMinus(){
		if (interval > lowerLimit && interval <= higherLimit){
			interval -= minusTime;
		}
	}
	
	//TODO: DRY-principle for interval2
	
	/**
	 * Add a bit of extra time to the next timer.
	 * Must be between the two limits.
	 */
	public static void doPlus2(){
		if (interval2 >= lowerLimit && interval2 < higherLimit){
		interval2 += plusTime;
		}
	}
	
	/**
	 * Subtract a bit of extra time from the next timer.
	 * Must be between the two limits.
	 */
	public static void doMinus2(){
		if (interval2 > lowerLimit && interval2 <= higherLimit){
			interval2 -= minusTime;
		}
	}
	
	/**
	 * clear the user comments
	 */
	public static void clearComments(){
		comments = "";
	}
	
	/**
	 * add user comment
	 * 
	 * @param c the user comment to add
	 */
	public static void addToComments(String c){
		if (comments.isEmpty()){
			comments = c;
			return;
		}
		checkAndLimitUserComments();
		comments = c + "<BR/>" + comments;
	}
	
	/**
	 * check the number of lines with user comments and limit them (remove the first one) if necessary
	 * 
	 * This method checks if the number of lines is not going to exceed the
	 * maximum number when you add one. ie.: this function limits the user
	 * comments to maxLinesUserComments minus one. Although a small effect, but
	 * it limits the memory footprint and should reduce a couple of computing
	 * cycles.
	 */
	static private void checkAndLimitUserComments(){
		String[] l_comments = comments.split("<BR/>");
		if (l_comments.length > maxLinesUserComments -1 ){
			comments = l_comments[maxLinesUserComments - 2];
			for (int i = maxLinesUserComments - 2 -1; i >= 0; i--){
				comments = l_comments[i] + "<BR/>" + comments;
			}
		}
	}
}