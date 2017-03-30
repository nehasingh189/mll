package mll.dao;

import org.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by William Guo on 3/30/2017.
 */
public class BandDaoTest {
  @Test
  public void testValidMusicianId() {
    BandDao bandDao = new BandDao();
    JSONArray bands = bandDao.getBands(94);
    assertEquals(bands.length(), 8);
  }

  @Test
  public void testBandDaoWithInvalidId() {
    BandDao bandDao = new BandDao();
    JSONArray bands = bandDao.getBands(-1);
    assertEquals(bands, new JSONArray());
  }
}
