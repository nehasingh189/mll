package mll.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;


import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class ProfileServiceTest {


  @Test
  public void populateSongWritersnull() throws Exception {
    ProfileService service = new ProfileService();
    assertEquals(true, service.populateSongWriters(null, null) == null);
  }

  @Test
  public void populateSongWriterssuccess() throws Exception {
    ProfileService service = new ProfileService();
    JSONObject json = new JSONObject();
    assertEquals(true, service.populateSongWriters(json, null).toString().equals("{}"));
  }

  @Test
  public void testUploadBandDetailsNoRequest() {
    ProfileService service = new ProfileService();
    JSONObject response = service.uploadBandDetails(null, null);
    assertNull(response);
  }

  @Test
  public void testPopulateSongWritersWithNullOwnershipInfo() throws Exception {
    ProfileService service = new ProfileService();
    JSONObject ownerAndBand = new JSONObject();
    ownerAndBand.put("key", "value");
    JSONObject response = service.populateSongWriters(ownerAndBand, null);
    assertSame(response, ownerAndBand);
  }

  @Test
  public void testPopulateSongWritersWithValidOwnerShipInformation() {
    ProfileService service = new ProfileService();
    JSONObject ownerAndBand = new JSONObject();
    JSONObject ownershipInformation = new JSONObject();
    ownershipInformation.put("bandName", "Marilyn Manson");
  }

}
