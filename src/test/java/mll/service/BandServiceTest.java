package mll.service;

import org.json.JSONArray;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by William Guo on 3/30/2017.
 */
public class BandServiceTest {
  @Test
  public void testGetBandsValidRequest() {
    BandService service = new BandService();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    HttpSession session = mock(HttpSession.class);
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("musician_id")).thenReturn(94);
    JSONArray bands = service.getBands(request, response);
    assertEquals(bands.length(), 8);
  }

  @Test
  public void testGetBandsInvalidRequest() {
    BandService service = new BandService();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("musician_id")).thenReturn(-1);
    JSONArray bands = service.getBands(request, response);
    assertEquals(bands, new JSONArray());
  }

  @Test(expected = ClassCastException.class)
  public void testGetbandsWithNotAnId() {
    BandService service = new BandService();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("musician_id")).thenReturn("Not a number");
    JSONArray bands = service.getBands(request, response);
  }
}
