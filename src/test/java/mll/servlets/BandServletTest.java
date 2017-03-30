package mll.servlets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
/**
 * Created by William Guo on 3/30/2017.
 */
public class BandServletTest {
  HttpServletRequest request;
  HttpServletResponse response;
  HttpSession session;
  StringWriter stringWriter;
  PrintWriter writer;
  BandServlet servlet;

  @Before
  public void init() {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    session = mock(HttpSession.class);
    when(request.getSession()).thenReturn(session);

    stringWriter = new StringWriter();
    writer = new PrintWriter(stringWriter);

    servlet = new BandServlet();
  }
  @Test
  public void testDoGetWithValidMusicianIds() {
    try {
      servlet.init();
      when(response.getWriter()).thenReturn(writer);
      when(session.getAttribute("musician_id")).thenReturn(94);
      servlet.doGet(request, response);

      JSONArray responseObject = new JSONArray(stringWriter.toString());
      assertEquals(responseObject.length(), 8);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDoGetWithInvalidMusicianIds() {
    try {
      servlet.init();
      when(response.getWriter()).thenReturn(writer);
      when(session.getAttribute("musician_id")).thenReturn(-1);
      servlet.doGet(request, response);

      JSONArray responseObject = new JSONArray(stringWriter.toString());
      assertEquals(responseObject.length(), 0);
      assertEquals(responseObject, new JSONArray());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test(expected = ClassCastException.class)
  public void testDoGetWithNotAnId() {
    try {
      servlet.init();
      when(response.getWriter()).thenReturn(writer);
      when(session.getAttribute("musician_id")).thenReturn("not an int");
      servlet.doGet(request, response);
    } catch (ServletException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
