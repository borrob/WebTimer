package com.webtimer.timer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webtimer.timer.CountdownTimer;


@WebServlet("/AjaxTimer")
public class AjaxTimerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private CountdownTimer cdt;

    public AjaxTimerServlet() {
    	super();
    	cdt = new CountdownTimer();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(cdt.countdown);
		response.getWriter().append("" + cdt.countdown/1000);
	}

}
