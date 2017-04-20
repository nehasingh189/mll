package mll.dao;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import mll.beans.Band;
import mll.utility.SessionFactoryUtil;

/**
 * Created by William Guo on 3/30/2017.
 */
public class BandDao {


  public JSONArray getBands(int musicianId) {
    Session session = null;
    Transaction tx = null;

    JSONArray bandArray = new JSONArray();

    try {
      session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
      tx = session.beginTransaction();
      ObjectMapper mapper = new ObjectMapper();

      Query query = session.createQuery("FROM mll.beans.Band a WHERE a.musician_id=:id");
      query.setParameter("id", musicianId);

      List<Band> bands = query.list();

      for (Band band : bands) {
        JSONObject obj = new JSONObject(mapper.writeValueAsString(band));
        bandArray.put(obj);
      }

      session.disconnect();
      return bandArray;

    } catch (Exception e) {
      e.printStackTrace();
      session.disconnect();
      return null;
    }
  }
}
