package mll.service;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.junit.Test;

public class LoginServiceTest {

    @Test
    public void testPopulateEmptyUserDetails() throws Exception {
        LoginService service = new LoginService();
        assertEquals(true, service.populateUserDetails(null) == null);
    }

    @Test
    public void testPopulateUserDetailsForEmailID() throws Exception {
        LoginService service = new LoginService();
        assertEquals(true, service.populateUserDetails(getUser()).getUser().getEmailId().equals("email123"));
    }

    @Test
    public void testPopulateUserForPassword() throws Exception {
        LoginService service = new LoginService();
        assertEquals(true, service.populateUserDetails(getUser()).getUser().getPassword().equals("password123"));
    }

    @SuppressWarnings("unchecked")
    public JSONObject getUser() {
        JSONObject jo = new JSONObject();

        jo.put("userName", "username123");
        jo.put("emailId", "email123");
        jo.put("password", "password123");

        return jo;
    }
}
