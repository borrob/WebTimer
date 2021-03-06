package com.webtimer.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.webtimer.timer.CountdownTimer;

@WebServlet(urlPatterns = {"/Timer", "/timer"})
public class TimerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(TimerServlet.class);


	public TimerServlet() {
		super();
	}

	/* 
	 * Start the timer.
	 * 
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		logger.info("Starting up WebTimer and setting all the paramters van web.xml !");
		
		String defaultInterval = getServletContext().getInitParameter("default_interval");
		CountdownTimer.setDefaultInterval(Integer.valueOf(defaultInterval));
		String initialCountdown = getServletContext().getInitParameter("initial_countdown");
		CountdownTimer.setCountdown(Integer.valueOf(initialCountdown));
		String plusTime = getServletContext().getInitParameter("plus_time");
		CountdownTimer.setPlusTime(Integer.valueOf(plusTime));
		String minusTime = getServletContext().getInitParameter("minus_time");
		CountdownTimer.setMinusTime(Integer.valueOf(minusTime));
		String lowerLimit = getServletContext().getInitParameter("lower_limit");
		CountdownTimer.setLowerLimit(Integer.valueOf(lowerLimit));
		String higherLimit = getServletContext().getInitParameter("higher_limit");
		CountdownTimer.setHigherLimit(Integer.valueOf(higherLimit));
		String maxLinesUserComments = getServletContext().getInitParameter("max_lines_user_comments");
		CountdownTimer.setMaxLinesUserComments(Integer.valueOf(maxLinesUserComments));
		
		CountdownTimer.start();
	}

	/*
	 * Get the countdown time and forward to the jsp page.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.trace("Received a get timer request.");
		request.setAttribute("countdown", String.valueOf(CountdownTimer.getCountdown()));
		request.setAttribute("next_interval", String.valueOf(CountdownTimer.getInterval()));
		request.setAttribute("next_interval2", String.valueOf(CountdownTimer.getInterval2()));
		request.setAttribute("userComments", CountdownTimer.getComments());
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/timer.jsp");
		dispatcher.forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/* 
	 * Stop the timer.
	 * 
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy(){
		logger.info("Shutting down WebTimer");
		CountdownTimer.stop();
	}

}