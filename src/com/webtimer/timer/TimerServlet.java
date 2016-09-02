package com.webtimer.timer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webtimer.timer.CountdownTimer;

@WebServlet("/timer")
public class TimerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CountdownTimer cdt;

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
		System.out.println("Starting up WebTimer");
		cdt = new CountdownTimer();
		cdt.start();
	}

	/*
	 * Get the countdown time and forward to the jsp page.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		System.out.println("Shutting down WebTimer");
		cdt.stop();
	}

}