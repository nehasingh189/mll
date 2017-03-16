package mll.service;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import mll.beans.Owner;
import mll.utility.Configuration;

import mll.dao.ProfileDAO;

public class ProfileService {
  ProfileDAO dao;
  Configuration conf = new Configuration();

  public ProfileService() {
    dao = new ProfileDAO();
  }

  @SuppressWarnings("unchecked")
  public JSONObject uploadBandDetails(HttpServletRequest request, HttpServletResponse response) {
    if (request == null) {
      return null;
    }
    JSONObject responseObject = new JSONObject();
    JSONObject ownerAndBand;
    HttpSession session = request.getSession();
    Integer musician_id = (Integer) session.getAttribute("musician_id");
    try {
      ownerAndBand = populateMetadataBeansFromRequest(request);
      if (null != ownerAndBand && !ownerAndBand.isEmpty()) {
        dao.saveMetadata(ownerAndBand, musician_id);
        responseObject.put("isUploaded", true);
        responseObject.put("message", "Band Information successfully uploaded");

      } else {
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

  public JSONObject populateMetadataBeansFromRequest(HttpServletRequest request) throws Exception {
//		List<Metadata> metadatas = new ArrayList<Metadata>();
//		Metadata metadata =  new Metadata();
    JSONObject ownerAndBand = new JSONObject();
    StringBuffer requestStr = new StringBuffer();
    BufferedReader reader = request.getReader();
    String line;

    while ((line = reader.readLine()) != null) {
      requestStr.append(line);
    }

    JSONParser parser = new JSONParser();

    JSONObject mainObject = (JSONObject) parser.parse(requestStr.toString());
    JSONObject ownershipInformation = (JSONObject) mainObject.get("ownershipInformation");

    // Populate Song Writers Information
    populateSongWriters(ownerAndBand, ownershipInformation);

    return ownerAndBand;
  }

  @SuppressWarnings("unchecked")
  public JSONObject populateSongWriters(JSONObject OwnerandBand, JSONObject ownershipInformation) throws Exception {

    if (null == ownershipInformation) {
      return OwnerandBand;
    }

    JSONArray songwriters = (JSONArray) ownershipInformation.get("songwriters");
    OwnerandBand.put("bandName", ownershipInformation.get("bandName"));
    List<Owner> owners = new ArrayList<>();
    for (Object songwriter : songwriters) {
      JSONObject writer = (JSONObject) songwriter;
      System.out.println("...........................................");
      System.out.println(writer);
      System.out.println("...........................................");
      Owner owner = new Owner();

      owner.setDivisionOfOwnership("Half");
      owner.setName((String) writer.get("firstName") + " " + (String) writer.get("lastName"));
      owner.setOwnerType("WRITER");
      owner.setPrimaryEmail((String) writer.get("primaryEmail"));
      owner.setSecondaryEmail((String) writer.get("secondaryEmail"));
      owner.setPrimaryPhone((String) writer.get("primaryPhone"));
      owner.setSecondaryPhone((String) writer.get("secondaryPhone"));
      owner.setContribution((String) writer.get("contribution"));
      owner.setOwner_percent((Long) writer.get("ownershipPercent"));
      owner.setRole((String) writer.get("musicianRole"));

      owners.add(owner);
    }
    OwnerandBand.put("owners", owners);

    return OwnerandBand;
  }
}
