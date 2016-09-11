package com.webtimer.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.webtimer.timer.CountdownTimer;


@WebServlet(urlPatterns = {"/getData", "/getdata"})
public class GetData extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(GetData.class);

    public GetData() {
    	super();
    }

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * Returns a simpel text-string of the current countdown.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.trace("Received a getdata request.");
		response.setContentType("application/text;charset=UTF-8");
		response.getWriter().append(String.valueOf(CountdownTimer.getCountdown()));
		response.getWriter().append("_").append(String.valueOf(CountdownTimer.getInterval()));
		response.getWriter().append("_").append(String.valueOf(CountdownTimer.getInterval2()));
		response.getWriter().append("_").append(CountdownTimer.getComments());
	}

}
