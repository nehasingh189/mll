package mll.servlet;

import static org.junit.Assert.assertEquals;
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
import mll.servlets.LoginServlet;
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
    public void testHandleInvalidLogin() throws ServletException {
        try {
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);
            HttpSession session = mock(HttpSession.class);

            JSONObject loginObject = new JSONObject();
            loginObject.put("userName", "InvalidUser");
            loginObject.put("password", "invalidpass");

            when(request.getSession()).thenReturn(session);
            when(request.getReader()).thenReturn(new BufferedReader(new StringReader(loginObject.toString())));

            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            when(response.getWriter()).thenReturn(writer);

            loginServlet.doGet(request, response);

            JSONObject responseObject = new JSONObject(stringWriter.toString());
            assertEquals(false, responseObject.getBoolean("isValidUser"));
            assertEquals("Username and/or password doesn't match. Please provide valid credentials.",
                    responseObject.getString("errorMessage"));
        }
        catch(Exception e) {
            fail();
        }
    }
}
