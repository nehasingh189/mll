package mll.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mll.beans.Invite;

public class InviteDAOTest {

    @Test
    public void testGenerateInviteWithEmptyInvite() {
        try {
            InviteDAO dao = new InviteDAO();
            assertEquals(true, dao.generateInvite(null) == null);
        } catch (Exception e) {
        }
    }

    @Test
    public void testGenerateInviteInitialState() {
        try {
            InviteDAO dao = new InviteDAO();
            Invite invite = dao.generateInvite(new Invite());
            assertEquals(true, !invite.getIsGenerated());
            assertEquals(true, invite.getToken().getToken() == null);
        } catch (Exception e) {
        }
    }

    @Test
    public void testValidateInviteWithEmptyInvite() {
        try {
            InviteDAO dao = new InviteDAO();
            assertEquals(true, dao.validateInvite(null) == null);
        } catch (Exception e) {
        }
    }

    @Test
    public void testValidateInviteInitialState() {
        try {
            InviteDAO dao = new InviteDAO();
            Invite invite = dao.validateInvite(new Invite());
            assertEquals(true, invite.getIsValid() == false);
            assertEquals(true, invite.getMessage().equals("Invalid Token."));
        } catch (Exception e) {
        }
    }

    @Test
    public void testCheckEmail1() {

        try {
            InviteDAO dao = new InviteDAO();
            assertEquals(true, dao.checkEmailId(null) == true);
            assertEquals(true, dao.checkEmailId("") == true);
            assertEquals(true, dao.checkEmailId("xyz@gmail.com") == true);
            assertEquals(true, dao.checkEmailId("testAdmin@gmail.com") == false);
            assertEquals(true, dao.checkEmailId("testMusician@gmail.com") == false);
        } catch (Exception e) {
        }
    }

}
