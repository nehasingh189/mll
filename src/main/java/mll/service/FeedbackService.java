package mll.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mll.beans.Feedback;
import mll.dao.FeedBackDAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class FeedbackService {
	/**
	 * This method takes HTTP request and response objects and sends
	 * the request contents as an email to the feedback email
	 */
	FeedBackDAO feedbackDAO;
	public FeedbackService(){
		feedbackDAO = new FeedBackDAO();
	}

	@SuppressWarnings("unchecked")
	public JSONObject uploadFeedbackDetails(HttpServletRequest request, HttpServletResponse response) {

		if (request == null) {
			return null;
		}
		JSONObject responseObject = new JSONObject();
		JSONObject feedBack;
		HttpSession session = request.getSession();
		//Integer musician_id = (Integer) session.getAttribute("musician_id");
		try {
			Feedback feedBackObj = this.populateMetadataBeansFromRequest(request);
			if (null != feedBackObj) {

				System.out.println(feedBackObj);

				feedbackDAO.saveMetadata(feedBackObj);
				responseObject.put("isUploaded", true);
				responseObject.put("message", "Band Information successfully uploaded");
			}
			else {
				responseObject.put("isUploaded", false);
				responseObject.put("message", "Request does not contain valid data. Please upload with proper metadata information.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.put("isUploaded", false);
			responseObject.put("message", "Error while saving the data. Please upload with proper band information.");
		}
		return responseObject;
	}

	private Feedback populateMetadataBeansFromRequest(HttpServletRequest request) throws IOException, ParseException, Exception {

		JSONObject feedback = new JSONObject();
		StringBuffer requestStr = new StringBuffer();
		BufferedReader reader = request.getReader();
		String line;

		while ((line = reader.readLine()) != null) {
			requestStr.append(line);
		}

		JSONParser parser = new JSONParser();

		JSONObject feedBackInformation = (JSONObject) parser.parse(requestStr.toString());
		//JSONObject feedBackInformation = (JSONObject) mainObject.get("feedbackData");

		Feedback feedbackObj = new Feedback();
		feedbackObj.setFeedbackmsg((String) feedBackInformation.get("feedbackString"));
		feedbackObj.setUserid(Integer.parseInt(feedBackInformation.get("userId").toString()));
		feedbackObj.setEmailId((String) feedBackInformation.get("emailId"));

		return feedbackObj;
	}


	@SuppressWarnings("unchecked")
	public org.json.JSONArray getFeedbacks(HttpServletRequest request, HttpServletResponse response) {

		return feedbackDAO.getAllFeedbacks();
	}


}
