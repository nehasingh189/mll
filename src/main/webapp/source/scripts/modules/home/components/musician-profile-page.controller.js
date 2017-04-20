(function (angular) {
    'use strict';
    angular
        .module("mllApp.home")
        .controller("MusicianProfilePageController", MusicianProfilePageController);

    MusicianProfilePageController.$inject =
        ['$scope', '$state', '$location', 'musicianProfilePageSerivce',
         'authenticationService'];

    function MusicianProfilePageController($scope, $state, $location,
                                           musicianProfilePageSerivce,
                                           authenticationService) {

//      this.firstName = "Medhavi";
//      this.lastName = "Mahansaria";
//      this.email = "medhavi@gmail.com"

        this.user = {
            userId: +this.userId,
            details: '',
            bands: []
        };

        this.authService = authenticationService;
        var userId = this.authService.details.data.id;

        // Promise that returns the list of bands
        musicianProfilePageSerivce.getBandsActual()
            .then((response) => {
                this.user.bands = response;
            })
            .catch((rejection) => {
                rejection;
            });

        this.user.details = musicianProfilePageSerivce.getProfile(this.user.userId);

        this.user.bands = musicianProfilePageSerivce.getBandsActual(this.user.userId);
        //this.user.bands = this.authService.details.data.bands;
        //musicianProfilePageSerivce.getBandsActual(this.user.userId);
        this.addBand = () => {
            $state.go("addBand", {id: userId}, {reload: true});
        }
    }
})(window.angular);