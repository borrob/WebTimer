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

/**
 * Servlet implementation class TimerComment
 */
@WebServlet(urlPatterns={"/TimerComment", "/timercomment"})
public class TimerComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(TimerComment.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimerComment() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/timer");	
		dispatcher.forward(request,response);
	}
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameterMap().containsKey("userCommentInput")){
			if (logger.isDebugEnabled()){logger.debug("Received a user comment: " + request.getParameter("userCommentInput") + " VAE A VERY HAPPY ONE!");}
			CountdownTimer.addToComments(request.getParameter("userCommentInput"));
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/timer");	
		dispatcher.forward(request,response);
	}

}
