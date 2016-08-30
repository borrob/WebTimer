package com.webtimer.timer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webtimer.timer.CountdownTimer;


@WebServlet("/getNextCountdown2")
public class GetNextCountdown2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public GetNextCountdown2() {
    	super();
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * Returns a simpel text-string of the next interval.
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(String.valueOf(CountdownTimer.getInterval2()/1000));
	}

}
