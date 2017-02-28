package mll.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by trevyn on 2/24/17.
 */
public class LoginServletTest {

    private LoginServlet loginServlet;

    @Before
    public void setup() {
        try {
            ServletConfig config = mock(ServletConfig.class);
            loginServlet = new LoginServlet();
            loginServlet.init(config);
        }
        catch (ServletException e) {
            System.err.println("Failed to initialize LoginServlet");
        }
    }

    @Test
    public void testHandleInvalidLogin() {
        try {
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);
            HttpSession session = mock(HttpSession.class);

            JSONObject loginObject = new JSONObject();
            loginObject.put("emailId", "InvalidUser@invalid.com");
            loginObject.put("password", "invalidpass");

            when(request.getSession()).thenReturn(session);
            when(request.getReader()).thenReturn(new BufferedReader(new StringReader(loginObject.toString())));

            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            when(response.getWriter()).thenReturn(writer);

            loginServlet.doGet(request, response);

            JSONObject responseObject = new JSONObject(stringWriter.toString());
            assertEquals(false, responseObject.getBoolean("isValidUser"));
            assertEquals("Email and/or password doesn't match. Please provide valid credentials.",
                    responseObject.getString("errorMessage"));
        }
        catch(Exception e) {
            fail();
        }
    }

    @Test
    public void testHandleValidARUserLogin() {
        try {
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);
            HttpSession session = mock(HttpSession.class);

            JSONObject loginObject = new JSONObject();
            loginObject.put("emailId", "langsford.t@husky.neu.edu");
            loginObject.put("password", "Zwjxzd9n8cx85gNKSQ5vceh6");

            when(request.getSession()).thenReturn(session);
            when(request.getReader()).thenReturn(new BufferedReader(new StringReader(loginObject.toString())));

            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            when(response.getWriter()).thenReturn(writer);

            loginServlet.doGet(request, response);

            JSONObject responseObject = new JSONObject(stringWriter.toString());
            assertTrue(responseObject.getBoolean("isValidUser"));
            assertEquals("langsford.t@husky.neu.edu", responseObject.getString("email"));
        }
        catch(Exception e) {
            fail();
        }
    }

    @Test
    public void testHandleValidMusicianLogin() {
        try {
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);
            HttpSession session = mock(HttpSession.class);

            JSONObject loginObject = new JSONObject();
            loginObject.put("emailId", "tlangsford@gmail.com");
            loginObject.put("password", "zoFDLNYwoZ#KlSpqmQIDh^9O");

            when(request.getSession()).thenReturn(session);
            when(request.getReader()).thenReturn(new BufferedReader(new StringReader(loginObject.toString())));

            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            when(response.getWriter()).thenReturn(writer);

            loginServlet.doGet(request, response);

            JSONObject responseObject = new JSONObject(stringWriter.toString());
            assertTrue(responseObject.getBoolean("isValidUser"));
            assertEquals("tlangsford@gmail.com", responseObject.getString("email"));
            assertEquals("musician", responseObject.getString("type"));
        }
        catch(Exception e) {
            fail();
        }
    }

    @Test
    public void testHandleValidLoginPost() {
        try {
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);
            HttpSession session = mock(HttpSession.class);

            JSONObject loginObject = new JSONObject();
            loginObject.put("emailId", "langsford.t@husky.neu.edu");
            loginObject.put("password", "Zwjxzd9n8cx85gNKSQ5vceh6");

            when(request.getSession()).thenReturn(session);
            when(request.getReader()).thenReturn(new BufferedReader(new StringReader(loginObject.toString())));

            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            when(response.getWriter()).thenReturn(writer);

            loginServlet.doPost(request, response);

            JSONObject responseObject = new JSONObject(stringWriter.toString());
            assertTrue(responseObject.getBoolean("isValidUser"));
            assertEquals("langsford.t@husky.neu.edu", responseObject.getString("email"));
        }
        catch(Exception e) {
            fail();
        }
    }
}
