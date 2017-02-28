package mll.service;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.junit.Test;

import mll.beans.Musician;
import mll.beans.Token;
import mll.beans.User;

public class RegistrationServiceTest {
    @Test
    public void testPopulateUser() throws Exception {
        RegistrationService service = new RegistrationService();
        User user = new User();

        assertEquals(true, service.populateUser(null) == null);
        assertEquals(false, service.populateUser(null) == user);

        assertEquals(true, service.populateUser(getUserData()).getEmailId().equals("testUser123@gmail.com"));
        assertEquals(true, service.populateUser(getUserData()).getPassword().equals("password"));
    }

    @Test
    public void testPopulateUserDoesNotSetId() throws Exception {
        RegistrationService service = new RegistrationService();

        assertEquals(true, service.populateUser(getUserData()).getId() == null);
    }

    @Test
    public void testPopulateMusician() throws Exception {
        RegistrationService service = new RegistrationService();
        Musician musician = new Musician();

        // Note this test is using the overloaded version of the populateMusician method, which exists specifically for tests
        assertEquals(false, service.populateMusician(null, true) == musician);
        assertEquals(true, service.populateMusician(null, true) == null);

        assertEquals(true, service.populateMusician(getMusicianData(), true).getId() == null);
        assertEquals(true, service.populateMusician(getMusicianData(), true).getName().equals("testUser123@gmail.com"));
    }

    @Test
    public void testPopulateAdminUser() throws Exception {
        RegistrationService service = new RegistrationService();

        assertEquals(true, service.populateAdminUser(null) == null);
        assertEquals(false, service.populateAdminUser(getAdminData()) == null);
        assertEquals(true, service.populateAdminUser(getAdminData()).getAge() == 24L);
        assertEquals(true, service.populateAdminUser(getAdminData()).getCollege().equals("NEU"));
        assertEquals(true, service.populateAdminUser(getAdminData()).getFirstName().equals("John"));
        assertEquals(true, service.populateAdminUser(getAdminData()).getLastName().equals("Smith"));
        assertEquals(true, service.populateAdminUser(getAdminData()).getGender().equals("Female"));
        assertEquals(true, service.populateAdminUser(getAdminData()).getId() == null);
        assertEquals(true, service.populateAdminUser(getAdminData()).getPreference().equals("none"));
    }

    @Test
    public void testPopulateToken() throws Exception {
        RegistrationService service = new RegistrationService();
        Token t = new Token();

        assertEquals(true, service.populateToken(null) == null);
        assertEquals(false, service.populateToken(getTokenData()).getToken() == null);
        assertEquals(false, service.populateToken(null) == t);
        assertEquals(true, service.populateToken(getTokenData()).getToken().equals("MK12"));
        assertEquals(true, service.populateToken(getTokenData()).getInviteType().equals("1"));
    }

    @SuppressWarnings("unchecked")
    public JSONObject getUserData() {
        JSONObject jo = new JSONObject();
        jo.put("password", "password");
        jo.put("emailId", "testUser123@gmail.com");
        return jo;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getAdminData() {
        JSONObject jo = new JSONObject();
        jo.put("firstName", "John");
        jo.put("lastName", "Smith");
        jo.put("college", "NEU");
        jo.put("level", "12");
        jo.put("gender", "Female");
        jo.put("preference", "none");
        jo.put("age", 24L);
        return jo;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getMusicianData() {
        JSONObject jo = new JSONObject();
        jo.put("emailId", "testUser123@gmail.com");
        return jo;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getTokenData() {
        JSONObject jo = new JSONObject();
        jo.put("token", "MK12");
        jo.put("type", "1");

        return jo;
    }
}
