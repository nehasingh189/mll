package mll.service;

import org.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mll.dao.BandDao;

/**
 * Created by William Guo on 3/30/2017.
 */
public class BandService {
  BandDao bandDao;

  public BandService() {
    bandDao = new BandDao();
  }

  public JSONArray getBands(HttpServletRequest request, HttpServletResponse response) {

    int musicianId = (int) request.getSession().getAttribute("musician_id");
    return bandDao.getBands(musicianId);
  }
}
