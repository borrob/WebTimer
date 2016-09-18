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
	 * Returns a simpel json object of the current countdown.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.trace("Received a getdata request.");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append("{");
		response.getWriter().append("\"countdown\": " + String.valueOf(CountdownTimer.getCountdown()) + ",");
		response.getWriter().append("\"interval\": " + String.valueOf(CountdownTimer.getInterval()) + ",");
		response.getWriter().append("\"interval2\": " + String.valueOf(CountdownTimer.getInterval2()) + ",");
		response.getWriter().append("\"comments\": \"" + CountdownTimer.getComments() + "\"");
		response.getWriter().append("}");
	}

}
