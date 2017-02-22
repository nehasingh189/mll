package mll.service;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import mll.beans.ARuser;
import mll.beans.AdminUser;
import mll.beans.Musician;
import mll.beans.Token;
import mll.beans.User;
import mll.beans.UserDetails;
import mll.dao.RegistrationDAO;
import mll.utility.Encryption;

public class RegistrationService {
    RegistrationDAO dao;

    public RegistrationService() {
        dao = new RegistrationDAO();
    }

    @SuppressWarnings("unchecked")
    public JSONObject register(HttpServletRequest request, HttpServletResponse response) {
        JSONObject responseObject = new JSONObject();
        try {
            StringBuffer requestStr = new StringBuffer();
            BufferedReader reader = request.getReader();
            HttpSession session = request.getSession();
            String line = null;
            while ((line = reader.readLine()) != null) {
                requestStr.append(line);
            }

            JSONParser parser = new JSONParser();
            JSONObject mainObject = (JSONObject) parser.parse(requestStr.toString());
            if (dao.checkAlreadyExists((String) mainObject.get("emailId"))) {
                responseObject.put("isRegistered", false);
                responseObject.put("errorMessage", "An account with that EmailId already exists. Please choose another emailId!");
                return responseObject;
            }
            UserDetails userdetails = populateUserDetailBeanFromRequest(mainObject, session);

            if (null != userdetails) {
                responseObject = dao.registerUser(userdetails);
            } else {
                responseObject.put("isRegistered", false);
                responseObject.put("errorMessage", "Error while registration. Please submit with proper user details.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseObject.put("isRegistered", false);
            responseObject.put("errorMessage", "Error while registration. Please submit with proper user details.");
        }
        return responseObject;
    }

    /**
     * This method takes http request as input and validates the request and if
     * it is valid then creates the metadata list from the request object.
     *
     * @author Dhaval Patel
     * @version 1.0
     * @since 2016-03-24
     */
    public UserDetails populateUserDetailBeanFromRequest(JSONObject mainObject, HttpSession session) throws Exception {

        UserDetails userdetails = new UserDetails();

        System.out.println(mainObject);
        System.out.println(mainObject.get("type"));
        userdetails.setToken(populateToken(mainObject));
        userdetails.setType((String) mainObject.get("type"));

        userdetails.setUsers(populateUser(mainObject));

        // Calling Encryption method to encrypt the password & Setting the encrypted password
        userdetails.getUsers().setPassword(Encryption.encryptPassword(userdetails.getUsers().getPassword()));

        if (userdetails.getType().equalsIgnoreCase("user")) {
            userdetails.getUsers().setUserType("aruser");
            //userdetails.setAdminUser(populateAdminUser(mainObject));

            userdetails.setAruser(populateARUser(mainObject));
        } else {
            userdetails.getUsers().setUserType("musician");
            userdetails.setMusician(populateMusician(mainObject));
            session.setAttribute("folder_id", userdetails.getMusician().getFolderId());
        }

        return userdetails;
    }

    /**
     * Creates a new User from the given data
     * @param jo JSON object with the following required fields:
     *           emailId
     *           password
     * @return User
     */
    public User populateUser(JSONObject jo) {
        User u = null;
        if (null != jo) {
            u = new User();
            u.setUserName((String) jo.get("emailId"));
            u.setPassword((String) jo.get("password"));
            u.setEmailId((String) jo.get("emailId"));
        }
        return u;
    }

    /**
     * Creates a new AdminUser from the given data
     * @param jo JSON object with the following required fields:
     *           firstName
     *           lastName
     *           college
     *           level
     *           gender
     *           major
     *           preference
     *           age
     * @return AdminUser
     */
    public AdminUser populateAdminUser(JSONObject jo) {
        AdminUser au = null;
        if (null != jo) {
            au = new AdminUser();
            au.setFirstName((String) jo.get("firstName"));
            au.setLastName((String) jo.get("lastName"));
            au.setCollege((String) jo.get("college"));
            au.setLevel((String) jo.get("level"));
            au.setGender((String) jo.get("gender"));

            if (null != jo.get("major") && !"".equals(jo.get("major"))) {
                au.setMajor((String) jo.get("major"));
            } else {
                au.setMajor(" ");
            }

            if (null != jo.get("minor") && !"".equals(jo.get("minor"))) {
                au.setMinor((String) jo.get("minor"));
            } else {
                au.setMinor(" ");
            }

            au.setPreference((String) jo.get("preference"));
            au.setAge(((Long) jo.get("age")).intValue());
        }
        return au;
    }

    /**
     * This method not currently used, as admin users serve the purpose of ARUsers
     */
    public ARuser populateARUser(JSONObject jo) {
        ARuser au = null;
        if (null != jo) {
            au = new ARuser();
            au.setFirstName((String) jo.get("firstName"));
            au.setLastName((String) jo.get("lastName"));
            au.setCollege((String) jo.get("college"));
            au.setLevel((String) jo.get("level"));
            au.setGender((String) jo.get("gender"));

            if (null != jo.get("major") && !"".equals(jo.get("major"))) {
                au.setMajor((String) jo.get("major"));
            } else {
                au.setMajor(" ");
            }

            if (null != jo.get("minor") && !"".equals(jo.get("minor"))) {
                au.setMinor((String) jo.get("minor"));
            } else {
                au.setMinor(" ");
            }

            au.setPreference((String) jo.get("preference"));
            au.setAge(((Long) jo.get("age")).intValue());
        }
        return au;
    }

    /**
     * Creates a new Musician from the given data
     * @param jo JSON object with the following required fields:
     *           emailId
     * @return Musician
     */
    public Musician populateMusician(JSONObject jo) {
        RazunaService rs = new RazunaService();
        Musician m = null;
        if (null != jo) {
            m = new Musician();
            m.setName(jo.get("emailId").toString());
            m.setFolderId(rs.createFolderForUser(m.getName()));
        }
        return m;
    }

    /**
     * This is an overloaded method for the above populate musician
     * This exists so that when running tests, a new razuna folder isn't created
     * for the test musician
     */
    public Musician populateMusician(JSONObject jo, Boolean isTest) {
        Musician m = null;
        if (isTest && null != jo) {
            m = new Musician();
            m.setName(jo.get("emailId").toString());
        }
        return m;
    }


    public Token populateToken(JSONObject jo) {
        Token t = null;
        if (null != jo) {
            t = new Token();
            t.setToken((String) jo.get("token"));
            t.setInviteType((String) jo.get("type"));
        }
        return t;
    }
}