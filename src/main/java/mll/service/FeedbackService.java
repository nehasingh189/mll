package mll.service;

import java.io.BufferedReader;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class FeedbackService {
	/**
	 * This method takes HTTP request and response objects and sends
	 * the request contents as an email to the feedback email
	 */
	@SuppressWarnings("unchecked")
	public JSONObject handleFeedbackRequest(HttpServletRequest request, HttpServletResponse response) {

		JSONObject responseObject = new JSONObject();

			try {
	            StringBuffer requestStr = new StringBuffer();
	            BufferedReader reader = request.getReader();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                requestStr.append(line);
	            }

	            String feedback = requestStr.toString();
	            
	            if (feedback != null && feedback != "") {
	            	 MailService mailService = new MailService();
	            	 mailService.sendFeedback(feedback);
	            }
	           
	            
	        } 
			catch (Exception e) {
	            e.printStackTrace();
	            // Error message will be set from the main method.
	            responseObject.put("success", false);
	            return responseObject;
	        }

		
		responseObject.put("success", true);
		return responseObject;
	}
}
