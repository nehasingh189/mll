(function (angular) {
    'use strict';

    angular
        .module('mllApp.registration')
        .controller('MusicianRegistrationFormController', MusicianRegistrationFormController);

    MusicianRegistrationFormController.$inject = ['$state', 'registrationService', 'registrationTypes'];

    function MusicianRegistrationFormController($state, registrationService, registrationTypes) {
        const hash = window.location.hash;

        this.data = {
            type: registrationTypes.musician,
            // TODO: find out where this.inviteToken is being set
            //token: this.inviteToken
            token: hash.substr(hash.lastIndexOf("/") + 1)
        };

        this.form = {invalid: true, submitted: false};

        this.validate = (isChecked) => {
            this.form.invalid = !isChecked;
        };

        this.register = () => {

            if (this.form.invalid) {
                this.registrationForm.$submitted = true;
            } else if (this.registrationForm.$invalid) {
                this.registrationForm.$submitted = true;
            } else {
                registrationService.register(this.data)
                    .then((response) => this.processResponse(response))
                    .catch((rejection) => this.displayError(rejection));
            }
        };

        this.processResponse = (data) => {
            if (data.isRegistered) {
                this.redirect(data.userId);
            } else {
                this.displayError(data.errorMessage);
            }
        };

        this.redirect = (id) => $state.go(registrationTypes.musician, {id: id});

        this.displayError = (errorMessage) => {
            this.registrationForm.$serverError = true;
            this.errorMessage = errorMessage;
        };
    }
})(window.angular);
