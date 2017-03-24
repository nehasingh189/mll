package mll.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mll.beans.Owner;

public class ProfileServiceTest {


  @Test
  public void populateSongWritersNull() throws Exception {
    ProfileService service = new ProfileService();
    assertEquals(true, service.populateSongWriters(null, null) == null);
  }

  @Test
  public void populateSongWritersSuccess() throws Exception {
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
  public void testPopulateSongWritersWithValidOwnerShipInformation() throws Exception {
    ProfileService service = new ProfileService();
    JSONObject ownerAndBand = new JSONObject();
    JSONObject ownershipInformation = new JSONObject();
    JSONObject marilyn = new JSONObject();
    JSONArray songWriters = new JSONArray();

    ownershipInformation.put("bandName", "Marilyn Manson");



    marilyn.put("firstName", "Marilyn");
    marilyn.put("lastName", "Manson");
    marilyn.put("musicianRole", "Vocals");

    songWriters.add(marilyn);

    ownershipInformation.put("songwriters", songWriters);

    JSONObject returned = service.populateSongWriters(ownerAndBand, ownershipInformation);

    Owner marilynOwner = new Owner();
    marilynOwner.setDivisionOfOwnership("Half");
    marilynOwner.setName("Marilyn Manson");
    marilynOwner.setOwnerType("WRITER");
    marilynOwner.setPrimaryEmail(null);
    marilynOwner.setSecondaryEmail(null);
    marilynOwner.setPrimaryPhone(null);
    marilynOwner.setSecondaryPhone(null);
    marilynOwner.setContribution(null);
    marilynOwner.setOwner_percent(null);
    marilynOwner.setRole("Vocals");
    List<Owner> owners = new ArrayList();
    owners.add(marilynOwner);

    assertSame((List<Owner>) returned.get("owners"), owners);
  }

}
