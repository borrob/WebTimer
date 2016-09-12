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

import com.webtimer.timer.CountdownTimer;

/**
 * Servlet implementation class AnneTimes
 */
@WebServlet({ "/AnneTimes", "/annetimes" })
public class AnneTimes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		List<Integer> anneTimes = new ArrayList<Integer>();
		anneTimes = new ArrayList<Integer>();
		for (int q = 120; q>=10; q-=10){
			anneTimes.add(q*1000);
		}
		CountdownTimer.setAnneTimes(anneTimes);
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

}
