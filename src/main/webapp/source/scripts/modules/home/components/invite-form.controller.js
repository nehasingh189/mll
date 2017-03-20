(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('InviteFormController', InviteFormController);

    InviteFormController.$inject = ['$timeout', 'inviteTokenService', 'arUserInvitationBody', 'musicianInvitationBody', 'authenticationService'];

    function InviteFormController($timeout, inviteTokenService, arUserInvitationBody, musicianInvitationBody, authenticationService) {
        this.data = {
            userId: +authenticationService.details.data.id,
            messageBody: musicianInvitationBody // default to musician invitation
        };

        this.types = [
            { label: 'AR User', value: 'user' },
            { label: 'Musician', value: 'musician' }
        ];

        this.changeInviteMessage = () => {
            if (this.data.inviteType === 'user') {
                this.data.messageBody = arUserInvitationBody;
            } else if (this.data.inviteType === 'musician') {
                this.data.messageBody = musicianInvitationBody;
            }
        };

        this.invite = () => {
            if (this.form.$invalid) this.form.$submitted = true;

            else
                inviteTokenService
                    .generateToken(this.data)
                    .then((response) => {
                        if(response.data.isGenerated === false){
                            this.message = response.data.errorMessage;
                            
                        }else{
                            this.message = response.data.message;                           
                        }

                        this.isGenerated = response.data.isGenerated;

                        this.isOpen = true;

                        this.data.type = '';
                        this.data.email = '';
                        this.form.$submitted = false;

                        $timeout(() => this.isOpen = false, 5000);
                    })
                    .catch((rejection) => rejection);
        };
    }
})(window.angular);