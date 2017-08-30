package mll.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import mll.service.FeedbackService;

public class FeedbackServlet extends HttpServlet
{
	private static final long serialVersionUID = 20L;
	FeedbackService subService = null;
	
	public void init(ServletConfig config) throws ServletException 
	{
		subService = new FeedbackService();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  
	{
		FeedbackService feedbackService = new FeedbackService();
		JSONObject responseObject = feedbackService.uploadFeedbackDetails(request, response);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(responseObject);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}