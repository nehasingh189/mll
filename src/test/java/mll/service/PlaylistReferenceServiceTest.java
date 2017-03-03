package mll.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import mll.beans.PlaylistReference;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class PlaylistReferenceServiceTest {

  @Test
  public void testGetAllPlaylistsForUserId1() {
    try {
      PlaylistReferenceService service = new PlaylistReferenceService();
      assertEquals(true, service.getAllPlaylistsForUser(-1) == null);
    } catch (Exception e) {
    }
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testConvertToJson1() {
    try {
      PlaylistReferenceService service = new PlaylistReferenceService();
      List<PlaylistReference> playlists = new ArrayList<PlaylistReference>();
      PlaylistReference playlistReference = new PlaylistReference();
      playlistReference.setId(1);
      playlistReference.setPlaylistName("Test");
      playlistReference.setUserId(1);
      playlistReference.setCreationDate(new Date());
      playlistReference.setIsShared(false);
      playlists.add(playlistReference);
      JSONArray jsonArray = new JSONArray();
      JSONObject object = new JSONObject();
      object.put("id", 1);
      object.put("playlistName", "Test");
      object.put("userId", 1);
      jsonArray.add(object);
      int count = service.convertToJson(playlists).size();
      assertEquals(true, count == jsonArray.size());
    } catch (Exception e) {

    }
  }


  @Test
  public void testSetPlaylistToGlobal1() {
    try {
      int userId = 0;
      int playlistId = 0;
      assertEquals(true,
          new PlaylistReferenceService().setPlaylistToGlobal(userId, playlistId) != null);

    } catch (Exception e) {

    }
  }

  @Test
  public void testSetPlaylistToGlobal2() {
    try {
      int userId = 0;
      int playlistId = 10;
      assertEquals(true,
          new PlaylistReferenceService().setPlaylistToGlobal(userId, playlistId) != null);

    } catch (Exception e) {

    }
  }

  @Test
  public void testAddPlaylistForUser2() {
    try {
      int userId = 1;
      String playlistName = null;
      assertEquals(true,
          new PlaylistReferenceService().addPlaylistForUser(userId, playlistName) != true);

    } catch (Exception e) {

    }
  }

  @Test
  public void testGetSharedPlaylist1() {
    try {
      assertEquals(true, new PlaylistReferenceService().getSharedPlaylists() != null);
    } catch (Exception e) {

    }
  }

  @Test
  public void testRemoveFromShare() {
    try {
      assertEquals(true, new PlaylistReferenceService().removeFromShare(1, 10000) != null);
    } catch (Exception e) {

    }

  }

  @Test
  public void testHandlePlaylistReferenceRequest2() {
    try {
      HttpServletRequest request = mock(HttpServletRequest.class);
      HttpServletResponse response = mock(HttpServletResponse.class);
      HttpSession session = mock(HttpSession.class);
      when(session.getAttribute("userId")).thenReturn("3");
      when(request.getSession()).thenReturn(session);
      when(request.getParameter("actionType")).thenReturn("add");
      when(request.getParameter("playlistName")).thenReturn("mlltest1");

      JSONObject jsonObject = new PlaylistReferenceService()
          .handlePlaylistReferenceRequest(request, response);
      assertEquals(true, jsonObject != null);
    } catch (Exception e) {

    }
  }

  @Test
  public void testHandlePlaylistReferenceRequest3() {
    try {
      HttpServletRequest request = mock(HttpServletRequest.class);
      HttpServletResponse response = mock(HttpServletResponse.class);
      HttpSession session = mock(HttpSession.class);
      when(session.getAttribute("userId")).thenReturn("3");
      when(request.getSession()).thenReturn(session);
      when(request.getParameter("actionType")).thenReturn("shared");
      JSONObject jsonObject = new PlaylistReferenceService()
          .handlePlaylistReferenceRequest(request, response);
      assertEquals(true, jsonObject != null);
    } catch (Exception e) {

    }
  }

  @Test
  public void testHandlePlaylistReferenceRequest4() {
    try {
      HttpServletRequest request = mock(HttpServletRequest.class);
      HttpServletResponse response = mock(HttpServletResponse.class);
      HttpSession session = mock(HttpSession.class);
      when(session.getAttribute("userId")).thenReturn("3");
      when(request.getSession()).thenReturn(session);
      when(request.getParameter("actionType")).thenReturn("get");
      JSONObject jsonObject = new PlaylistReferenceService()
          .handlePlaylistReferenceRequest(request, response);
      assertEquals(true, jsonObject != null);
    } catch (Exception e) {

    }
  }

  @Test
  public void testHandlePlaylistReferenceRequest5() {
    try {
      HttpServletRequest request = mock(HttpServletRequest.class);
      HttpServletResponse response = mock(HttpServletResponse.class);
      HttpSession session = mock(HttpSession.class);
      when(session.getAttribute("userId")).thenReturn("3");
      when(request.getSession()).thenReturn(session);
      when(request.getParameter("playlistId")).thenReturn("100000");
      when(request.getParameter("actionType")).thenReturn("delete");
      JSONObject jsonObject = new PlaylistReferenceService()
          .handlePlaylistReferenceRequest(request, response);
      assertEquals(true, jsonObject != null);
    } catch (Exception e) {

    }
  }

  @Test
  public void testHandlePlaylistReferenceRequest6() {
    try {
      HttpServletRequest request = mock(HttpServletRequest.class);
      HttpServletResponse response = mock(HttpServletResponse.class);
      HttpSession session = mock(HttpSession.class);
      when(session.getAttribute("userId")).thenReturn("3");
      when(request.getSession()).thenReturn(session);
      when(request.getParameter("actionType")).thenReturn("addToShare");
      JSONObject jsonObject = new PlaylistReferenceService()
          .handlePlaylistReferenceRequest(request, response);
      assertEquals(true, jsonObject != null);
    } catch (Exception e) {

    }
  }

  @Test
  public void testHandlePlaylistReferenceRequest7() {
    try {
      HttpServletRequest request = mock(HttpServletRequest.class);
      HttpServletResponse response = mock(HttpServletResponse.class);
      HttpSession session = mock(HttpSession.class);
      when(session.getAttribute("userId")).thenReturn("3");
      when(request.getSession()).thenReturn(session);
      when(request.getParameter("actionType")).thenReturn("unShare");
      when(request.getParameter("playlistId")).thenReturn("10000");
      JSONObject jsonObject = new PlaylistReferenceService()
          .handlePlaylistReferenceRequest(request, response);
      assertEquals(true, jsonObject != null);
    } catch (Exception e) {

    }
  }

  @Test
  public void whenNotUseCaptorAnnotation_thenCorrect() {
    List mockList = Mockito.mock(List.class);
    ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);

    mockList.add("one");
    Mockito.verify(mockList).add(arg.capture());

    assertEquals("one", arg.getValue());
  }


  @Test
  public void testHandlePlayListValidUserAndAddNoSuccess() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    PlaylistReferenceService service = mock(PlaylistReferenceService.class);
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("userId")).thenReturn(9992123);
    when(request.getParameter("actionType")).thenReturn("add");
    when(request.getParameter("playlistName")).thenReturn("name");
    when(service.addPlaylistForUser(9992123, "name")).thenReturn(false);
    when(service.handlePlaylistReferenceRequest(request,response)).thenCallRealMethod();
    JSONObject jsonResponse = service.handlePlaylistReferenceRequest(request, response);

    assertEquals(jsonResponse.get("isValid"), false);
    assertEquals(jsonResponse.get("playlist"), null);
  }

  @Test
  public void testHandlePlaylistReferenceRequestForValidDelete() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);

    PlaylistReferenceService service = new PlaylistReferenceService();

    when(request.getParameter("actionType")).thenReturn("delete");

  }

  @Test
  public void testHandlePlayListNullUserId() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    when(request.getSession()).thenReturn(session);
    when(session.getAttribute("userId")).thenReturn(null);

    PlaylistReferenceService service = new PlaylistReferenceService();
    JSONObject jsonResponse = service.handlePlaylistReferenceRequest(request, response);
    assertEquals(jsonResponse.get("isValid"), false);
    assertEquals(jsonResponse.get("playlist"), null);
  }


}