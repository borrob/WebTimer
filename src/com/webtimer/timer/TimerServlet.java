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

	@Override
	public void init() throws ServletException {
		System.out.println("Starting up WebTimer");
		cdt = new CountdownTimer();
		cdt.start();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Get the countdown time and forward to the jsp page.
		 */
		request.setAttribute("countdown", "" + CountdownTimer.countdown);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/timer.jsp");
		dispatcher.forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy(){
		System.out.println("Shutting down WebTimer");
		cdt.stop();
	}

}
