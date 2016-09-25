package com.webtimer.timer;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class CountdownTimer {

	private static Timer timer;

	private final static int DELAY = 0;
	private final static int UPDATE_INTERVAL = 1;

	private static int defaultInterval;
	
	private static int interval;
	private static int interval2;
	private static List<Integer> anneTimes;
	private static boolean useAnneTimes = false;
	private static boolean useRandom = false;
	private static Random rand = new Random();
	
	private static int countdown = 3; //the time left over in the current interval
	private static String comments = "";
	public static boolean isRunning = false;
	
	/**
	 * Default values to change the interval with.
	 */
	private static int plusTime;
	private static int minusTime;
	
	/**
	 * The interval must be between <lowerLimit> and <higherLimit>
	 */
	private static int lowerLimit;
	private static int higherLimit;
	
	private static int maxLinesUserComments;
	
	private static final Logger logger = Logger.getLogger(CountdownTimer.class);

	//////////////////////////////////////////////////////////////////////////////////
	
	/* GETTER AND SETTERS */
	
	public static int getDefaultInterval() {
		return defaultInterval;
	}
	
	public static void setDefaultInterval(int defaultInterval) {
		CountdownTimer.defaultInterval = defaultInterval;
	}

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
	
	public static List<Integer> getAnneTimes() {
		return anneTimes;
	}

	public static void setAnneTimes(List<Integer> aT) {
		if (aT.size()>2){
			anneTimes = aT;
			setInterval(anneTimes.remove(0));
			setInterval2(anneTimes.remove(0));
			setCountdown(3);
			useAnneTimes=true;
			logger.debug("anneTimes and the intervals are set!");
			
			if (!isRunning){
				start();
			}
		}
	}
	
	public static void setRandom(boolean r) {
		useRandom = r;
		setInterval(randomInterval());
		setInterval2(randomInterval());
		setCountdown(3);
		logger.debug("Random intervals are set!");
		
		if (!isRunning){
			start();
		}
	}
	
	public static boolean getRandom() {
		return useRandom;
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
	
	public static int getPlusTime() {
		return plusTime;
	}

	public static void setPlusTime(int plusTime) {
		CountdownTimer.plusTime = plusTime;
	}

	public static int getMinusTime() {
		return minusTime;
	}

	public static void setMinusTime(int minusTime) {
		CountdownTimer.minusTime = minusTime;
	}

	public static int getLowerLimit() {
		return lowerLimit;
	}

	public static void setLowerLimit(int lowerLimit) {
		CountdownTimer.lowerLimit = lowerLimit;
	}

	public static int getHigherLimit() {
		return higherLimit;
	}

	public static void setHigherLimit(int higherLimit) {
		CountdownTimer.higherLimit = higherLimit;
	}

	public static int getMaxLinesUserComments() {
		return maxLinesUserComments;
	}

	public static void setMaxLinesUserComments(int maxLinesUserComments) {
		CountdownTimer.maxLinesUserComments = maxLinesUserComments;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	/*METHODS*/

	/**
	 * Start the timer and keep it going.
	 */
	public static boolean start(){
		logger.info("Starting the timer.");
		
		setTheIntervals();
		
		timer = new Timer();
		timer.scheduleAtFixedRate(
				new TimerTask(){
					@Override
					public void run(){
						timeStep();
						if (logger.isTraceEnabled()){logger.trace("Timer is at: " + String.valueOf(countdown));}
					}
				},
				DELAY * 1000, //miliseconds
				UPDATE_INTERVAL * 1000 //miliseconds
				);
		isRunning=true;
		return isRunning;
	}

	/**
	 * Stop the timer.
	 */
	public static boolean stop(){
		logger.info("Stopping the timer.");
		timer.cancel();
		isRunning=false;
		return isRunning;
	}
	
	/**
	 * Stop and reset the timer.
	 *
	 * Calls the stop() method and then resets the intervals and anneTimes.
	 */
	public static boolean stopAndReset(){
		stop();
		useAnneTimes=false;
		anneTimes = null;
		useRandom = false;
		interval = defaultInterval;
		interval2 = defaultInterval;
		countdown = 3;
		return isRunning;
	}

	/**
	 * Helper method to update the time left over in the current run.
	 * Reset interval when it runs out.
	 * 
	 * TODO: improve on DRY principle: deal with the different if-statements
	 */
	private static void timeStep(){
		if(isRunning){
			
			if (countdown>0){
				countdown -= UPDATE_INTERVAL;
			} else {
			
				if(logger.isTraceEnabled()){logger.trace("Using annetime? " + String.valueOf(useAnneTimes));}
				
				if (useAnneTimes){
					if (interval==999999){ //this must have been the last countdown of this set, because interval=999999
						stop();
						return;
					}
					countdown = interval;
					interval = interval2;
					if (!anneTimes.isEmpty()){ //go to the next interval in the list
						interval2 = anneTimes.remove(0);
					} else { //list ran out --> set indicator for end of list
						interval2 = 999999;
					}
				} else {
					if (useRandom){
						countdown = interval;
						interval = interval2;
						interval2 = randomInterval();
					}else { //not using anneTimes or random--> keep using the default interval
						countdown = interval;
						interval = interval2;
						interval2 = defaultInterval;
					}
					
				}
			}
		}
	}
	
	/**
	 * Add a bit of extra time to the next timer.
	 * Must be between the two limits.
	 */
	public static void doPlus(){ 
		if (interval >= lowerLimit && interval < higherLimit){
			if (logger.isDebugEnabled()){logger.debug("Added: " + String.valueOf(plusTime) + " to " + String.valueOf(interval) + " (interval).");}
			interval += plusTime;
		}
	}
	
	/**
	 * Subtract a bit of extra time from the next timer.
	 * Must be between the two limits.
	 */
	public static void doMinus(){
		if (interval > lowerLimit && interval <= higherLimit){
			if (logger.isDebugEnabled()){logger.debug("Substracting: " + String.valueOf(minusTime) + " from " + String.valueOf(interval) + " (interval).");}
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
			if (logger.isDebugEnabled()){logger.debug("Added: " + String.valueOf(plusTime) + " to " + String.valueOf(interval2) + " (interval2).");}
			interval2 += plusTime;
		}
	}
	
	/**
	 * Subtract a bit of extra time from the next timer.
	 * Must be between the two limits.
	 */
	public static void doMinus2(){
		if (interval2 > lowerLimit && interval2 <= higherLimit){
			if (logger.isDebugEnabled()){logger.debug("Substracting: " + String.valueOf(minusTime) + " from " + String.valueOf(interval2) + " (interval2).");}
			interval2 -= minusTime;
		}
	}
	
	/**
	 * clear the user comments
	 */
	public static void clearComments(){
		logger.debug("Clearing user comments.");
		comments = "";
	}
	
	/**
	 * add user comment
	 * 
	 * @param c the user comment to add
	 */
	public static void addToComments(String c){
		if (logger.isDebugEnabled()){logger.debug("Adding user comment: " + c);}
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
			logger.debug("Shortening user comments.");
			comments = l_comments[maxLinesUserComments - 2];
			for (int i = maxLinesUserComments - 2 -1; i >= 0; i--){
				comments = l_comments[i] + "<BR/>" + comments;
			}
		}
	}
	
	/**
	 * Set the interval and interval2 to either the first two times of anneTimes or the defatult.
	 */
	static private void setTheIntervals(){
		if (useAnneTimes && anneTimes != null && anneTimes.size()>2){
			interval = anneTimes.remove(0);
			interval2 = anneTimes.remove(0);
		} else {
			if (useRandom){
				interval = randomInterval();
				interval2 = randomInterval();
			} else {
				interval = defaultInterval;
				interval2 = defaultInterval;
			}	
		}
	}
	
	/**
	 * Generate a random interval (between lowerlimit and higherlimit and in steps op 10 secoonds.
	 * 
	 * @return a new random interval period (ms).
	 */
	private static int randomInterval(){
		int interval = 10*rand.nextInt((higherLimit-lowerLimit)/10) + lowerLimit;
		if (logger.isDebugEnabled()){logger.debug("New random interval: " + String.valueOf(interval));}
		return interval;
	}
}
