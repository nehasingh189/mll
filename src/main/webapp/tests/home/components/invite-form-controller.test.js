'use strict';

describe("Invite Form Controller:", function() {
    beforeEach(module('mllApp.home'));

    beforeEach(module('ui.router'));

    let ctrl, data, state;
    let arUserInvitationBody, musicianInvitationBody;

    beforeEach(inject(function($state) {
        state = $state;
    }));

    beforeEach(inject(function ($controller) {
        arUserInvitationBody = "arUserMessageBody";
        musicianInvitationBody = "musicianMessageBody";

        data = {
            userId: 100,
            messageBody: 'testMessageBody'
        };

        ctrl = $controller('InviteFormController',
            { arUserInvitationBody: arUserInvitationBody, musicianInvitationBody: musicianInvitationBody },
            { data: data });
    }));

    it("'data' should be defined", function() {
        expect(ctrl.data).toBeDefined();
    });

    it("the function changeInviteMessage should set the appropriate invite message for the invite type", function() {
        expect(ctrl.data.messageBody).toBe("testMessageBody");

        // Test for arUser
        ctrl.data.inviteType = 'user';
        ctrl.changeInviteMessage();
        expect(ctrl.data.messageBody).toBe('arUserMessageBody');

        // Test for musician
        ctrl.data.inviteType = 'musician';
        ctrl.changeInviteMessage();
        expect(ctrl.data.messageBody).toBe('musicianMessageBody');
    });

});