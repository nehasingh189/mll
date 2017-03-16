package mll.servlets;

import mll.service.InviteServiceTest;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by trevyn on 2/28/17.
 */
public class InviteServletTest {

    private InviteServlet inviteServlet;

    @Before
    public void setup() {
        try {
            ServletConfig config = mock(ServletConfig.class);
            inviteServlet = new InviteServlet();
            inviteServlet.init(config);
        }
        catch (ServletException e) {
            System.err.println("Failed to initialize InviteServlet");
        }
    }

    @Test
    public void testHandleInvite() {
        try {
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);
            HttpSession session = mock(HttpSession.class);

            org.json.simple.JSONObject inviteObject = InviteServiceTest.getInviteGenerateJsonObject();

            when(request.getSession()).thenReturn(session);
            when(request.getReader()).thenReturn(new BufferedReader(new StringReader(inviteObject.toString())));

            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            when(response.getWriter()).thenReturn(writer);

            inviteServlet.doGet(request, response);

            JSONObject responseObject = new JSONObject(stringWriter.toString());
            assertFalse(responseObject.getBoolean("isGenerated"));
            assertEquals("Duplicate invite being sent to the same Musician",
                    responseObject.getString("errorMessage"));
        }
        catch(Exception e) {
            fail();
        }
    }

    @Test
    public void testHandleInvitePost() {
        try {
            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);
            HttpSession session = mock(HttpSession.class);

            org.json.simple.JSONObject inviteObject = InviteServiceTest.getInviteGenerateJsonObject();

            when(request.getSession()).thenReturn(session);
            when(request.getReader()).thenReturn(new BufferedReader(new StringReader(inviteObject.toString())));

            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            when(response.getWriter()).thenReturn(writer);

            inviteServlet.doPost(request, response);

            JSONObject responseObject = new JSONObject(stringWriter.toString());
            assertFalse(responseObject.getBoolean("isGenerated"));
            assertEquals("Duplicate invite being sent to the same Musician",
                    responseObject.getString("errorMessage"));
        }
        catch(Exception e) {
            fail();
        }
    }

}
