(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('InviteFormController', InviteFormController);

    InviteFormController.$inject = ['$timeout', 'inviteTokenService', 'authenticationService'];

    function InviteFormController($timeout, inviteTokenService, authenticationService) {
        this.data = {
            userId: +authenticationService.details.data.id
        };

        this.types = [
            { label: 'AR User', value: 'user' },
            { label: 'Musician', value: 'musician' }
        ];

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