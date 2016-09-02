package com.webtimer.timer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webtimer.timer.CountdownTimer;

/**
 * Servlet implementation class TimerAction
 */
@WebServlet("/TimerAction")
public class TimerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimerAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameterMap().containsKey("action")) {
			String theAction = request.getParameter("action");
			switch (theAction) {
				case "plus":	CountdownTimer.doPlus();
								break;
				case "plus2":	CountdownTimer.doPlus2();
								break;
				case "minus":	CountdownTimer.doMinus();
								break;
				case "minus2":	CountdownTimer.doMinus2();
								break;
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/timer");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
