package com.webtimer.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.webtimer.timer.CountdownTimer;

/**
 * Servlet implementation class AnneTimes
 */
@WebServlet({ "/AnneTimes", "/annetimes" })
public class AnneTimes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AnneTimes.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnneTimes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!CountdownTimer.isRunning){
			CountdownTimer cdt = new CountdownTimer();
			cdt.start();
		}
		
		if (request.getParameterMap().containsKey("style")){
			String style = request.getParameter("style");
			switch(style) {
				case "120down":
					logger.debug("Setting the intervals to 120, 110, ... 10;");
					CountdownTimer.setAnneTimes(getTimes120down());
					break;
				case "11times75":
					logger.debug("Setting the intervals to 11 x 75 seconds.");
					CountdownTimer.setAnneTimes(getTimesN_T(11, 75));
					break;
				case "testing":
					logger.debug("Setting the intervals for testing purposes");
					CountdownTimer.setAnneTimes(getTimesN_T(3, 5));
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/timer");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO: get list of times to add and push that to CountdownTimer.class
		doGet(request, response);
	}
	
	/**
	 * Get a list of integers for the count down timer intervals
	 * 
	 * This list starts at 120 seconds, 110, 100, ... 10 seconds.
	 *  
	 * @return a list of integers with the time interval countdowns
	 */
	private List<Integer> getTimes120down(){
		List<Integer> theTimes = new ArrayList<Integer>();
		theTimes = new ArrayList<Integer>();
		for (int q = 120; q>=10; q-=10){
			theTimes.add(q*1000);
		}
		return theTimes;
	}
	
	/**
	 * Get a list of integers for the count down timer intervals
	 * 
	 * This list contains n times t seconds.
	 *  
	 * @param n number of times the interval should return
	 * @param t the interval in seconds
	 * @return a list of integers with the time interval countdowns
	 */
	private List<Integer> getTimesN_T(int n, int t){
		List<Integer> theTimes = new ArrayList<Integer>();
		theTimes = new ArrayList<Integer>();
		for (int q = 1; q<=n; q++){
			theTimes.add(t*1000);
		}
		return theTimes;
	}
}
