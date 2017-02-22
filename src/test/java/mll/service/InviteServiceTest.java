package mll.service;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.junit.Test;

import mll.beans.Invite;
import mll.beans.Token;


public class InviteServiceTest {
    @Test
    public void testPopulateInviteBeansFromEmptyRequest() throws Exception {
        InviteService service = new InviteService();
        Invite invite = new Invite();
        assertEquals(true, service.populateInviteBeansFromRequest(null, null) == null);
        assertEquals(true, service.populateInviteBeansFromRequest(invite, null) == invite);
        assertEquals(true, service.populateInviteBeansFromRequest(null, new JSONObject()) == null);
    }

    @Test
    public void testInitialStateForPopulateInviteBeansFromRequest() throws Exception {
        InviteService service = new InviteService();
        Invite invite = service.populateInviteBeansFromRequest(new Invite(), getInviteGenerateJsonObject());
        assertEquals(true, invite.getActiontype().equals("generate"));
        assertEquals(true, invite.getToken().getEmailId().equals("abc@xyz.com"));
        assertEquals(true, invite.getToken().getToken().equals(""));
        assertEquals(true, invite.getToken().getInviteType().equals("user"));
        assertEquals(true, invite.getToken().getUserId() == 1);
        assertEquals(true, invite.getToken().getIsUsed() == false);
        assertEquals(true, invite.getToken().getIssueDate() != null);
    }

    @Test
    public void testPopulateInviteBeansFromRequest11() throws Exception {
        InviteService service = new InviteService();
        Invite invite = service.populateInviteBeansFromRequest(new Invite(), getInviteValidateJsonObject());
        assertEquals(true, invite.getActiontype().equals("validate"));
        assertEquals(true, invite.getToken().getToken().equals("MLLTCKN11"));
        assertEquals(true, invite.getToken().getInviteType().equals("user"));
    }

    @Test
    public void testIsEmailDuplicate1() throws Exception {
        InviteService service = new InviteService();
        Invite invite = new Invite();
        Token token = new Token();
        token.setEmailId(null);
        invite.setToken(token);
        invite.setActiontype("generate");
        assertEquals(true, service.isEmailDuplicate(invite) == true);
    }

    @Test
    public void testIsEmailDuplicate2() throws Exception {
        InviteService service = new InviteService();
        Invite invite = new Invite();
        Token token = new Token();
        token.setEmailId("");
        invite.setToken(token);
        invite.setActiontype("generate");
        assertEquals(true, service.isEmailDuplicate(invite) == true);

    }

    @SuppressWarnings("unchecked")
    public JSONObject getInviteGenerateJsonObject() {
        JSONObject tokenJsonObject = new JSONObject();

        tokenJsonObject.put("actionType", "generate");
        tokenJsonObject.put("email", "abc@xyz.com");
        tokenJsonObject.put("inviteType", "user");
        tokenJsonObject.put("userId", 1L);

        return tokenJsonObject;
    }

    @SuppressWarnings("unchecked")
    public JSONObject getInviteValidateJsonObject() {
        JSONObject tokenJsonObject = new JSONObject();

        tokenJsonObject.put("actionType", "validate");
        tokenJsonObject.put("token", "MLLTCKN11");
        tokenJsonObject.put("inviteType", "user");

        return tokenJsonObject;
    }
}

	
