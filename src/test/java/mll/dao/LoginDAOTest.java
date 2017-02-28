package mll.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mll.beans.Login;
import mll.beans.User;

public class LoginDAOTest {
    @Test
    public void testNullValidateLogin() {
        try {
            LoginDAO dao = new LoginDAO();
            assertEquals(true, dao.validateLogin(null) == null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateLoginWithNullUser() {
        try {
            LoginDAO dao = new LoginDAO();
            Login login = new Login();
            login.setUser(null);
            assertEquals(true, dao.validateLogin(login) == login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateLoginWithNullUsername() {
        try {
            LoginDAO dao = new LoginDAO();
            Login login = new Login();
            login.getUser().setUserName(null);
            assertEquals(true, dao.validateLogin(login) == login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateLoginWithNullPassword() {
        try {
            LoginDAO dao = new LoginDAO();
            Login login = new Login();
            login.getUser().setPassword(null);
            assertEquals(true, dao.validateLogin(login) == login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateLoginWithEmptyUsername() {
        try {
            LoginDAO dao = new LoginDAO();
            Login login = new Login();
            login.getUser().setUserName("");
            assertEquals(true, dao.validateLogin(login) == login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateLoginWithEmptyPassword() {
        try {
            LoginDAO dao = new LoginDAO();
            Login login = new Login();
            login.getUser().setPassword("");
            assertEquals(true, dao.validateLogin(login) == login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateLoginWithBogusCredentials() {
        try {
            LoginDAO dao = new LoginDAO();
            Login login = new Login();
            login.getUser().setPassword("AnyPassword");
            login.getUser().setEmailId("AnyEmailID");
            assertEquals(true, dao.validateLogin(login).isValidUser() == false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateLoginWithARUser() {
        try {
            LoginDAO dao = new LoginDAO();
            Login login = new Login();
            User user = new User();
            user.setUserName("aruser");
            user.setEmailId("mahanths2@gmail,com");
            user.setPassword("dbb2ad553b54536d308f7ade07cefbe5");
            login.setUser(user);
            login.setCanBrowse(true);
            login.setCanUpload(false);

            assertEquals("admin", (dao.validateLogin(login)).getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testValidateLoginWithMusician() {
        try {
            LoginDAO dao = new LoginDAO();
            Login login = new Login();
            User user = new User();
            user.setEmailId("testMusician@gmail.com");
            user.setPassword("25d55ad283aa400af464c76d713c07ad");
            login.setUser(user);
            login.setCanBrowse(true);
            login.setCanUpload(false);

            assertEquals("musician", (dao.validateLogin(login)).getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateLoginWithAdmin() {
        try {
            LoginDAO dao = new LoginDAO();
            Login login = new Login();
            User user = new User();
            user.setEmailId("testAdmin@gmail.com");
            user.setPassword("25d55ad283aa400af464c76d713c07ad");
            login.setUser(user);
            login.setCanBrowse(true);
            login.setCanUpload(false);

            assertEquals("admin", (dao.validateLogin(login)).getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidateWithValidUsernameAndInvalidPassword() {
        try {
            LoginDAO dao = new LoginDAO();
            Login login = new Login();
            User user = new User();
            user.setEmailId("fakeUser@gmail.com");
            user.setPassword("11111111");
            login.setUser(user);
            login.setCanBrowse(true);
            login.setCanUpload(false);

            assertEquals("Email and/or password doesn't match. Please provide valid credentials.", (dao.validateLogin(login)).getErrMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
