package com.webtimer.timer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webtimer.timer.CountdownTimer;


@WebServlet("/getCurrentCountdown")
public class GetCurrentCountdown extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public GetCurrentCountdown() {
    	super();
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * Returns a simpel text-string of the current countdown.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(String.valueOf(CountdownTimer.getCountdown()/1000));
	}

}
