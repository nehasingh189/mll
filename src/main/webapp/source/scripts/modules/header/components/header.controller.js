(function (angular){
    'use strict';

    angular
        .module('mllApp.header')
        .controller('HeaderController', HeaderController);

    HeaderController.$inject =
        ['$state', 'homeLink','feedbackLink','feedbackListLink','loginLink', 'logoutLink', 'uploadLink', 'navigationLinks', 'authenticationService'];

    function HeaderController($state, homeLink, feedbackLink,feedbackListLink, loginLink, logoutLink, uploadLink, navLinks, authenticationService) {

        this.authService = authenticationService;


        //console.log(this.ismusician);

        this.homeLink = homeLink;
        this.loginLink = loginLink;
        this.logoutLink = logoutLink;
        this.uploadLink = uploadLink;
        this.feedbackLink = feedbackLink;
        this.navLinks = navLinks;
        this.feedbackListLink = feedbackListLink;


        this.logout = () => {
            this.authService.clear();

            $state.go(loginLink.href);
        };

        this.feedback = () => {
            $state.go(feedbackLink.href, {}, { reload: true });
        };

        this.feedbacklist = () => {
            $state.go(feedbackListLink.href, {}, { reload: true });
        };

        this.home = () => {
            $state.go(this.authService.details.data.type, { id: this.authService.details.data.id });
        };

        this.upload = () => {
            $state.go(uploadLink.href, {}, { reload: true });
        };
    }
})(window.angular);
