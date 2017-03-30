package mll.servlets;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mll.service.BandService;

/**
 * Created by William Guo on 3/30/2017.
 */
public class BandServlet {
  private static final long serialVersionUID = 1L;
  BandService service = null;

  public void init() throws ServletException {
    service = new BandService();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    JSONArray responseObject = service.getBands(request, response);

    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    out.print(responseObject);
    out.flush();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    doGet(request, response);
  }

}
