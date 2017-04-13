(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('BandAddPageController', BandAddPageController);

    BandAddPageController.$inject = ['$state', 'musicContributions', 'musicianRoles', 'bandData', 'musicianProfilePageSerivce', 'authenticationService', 'profileLink', '$timeout'];

    function BandAddPageController($state, musicContributions, musicianRoles, bandData, musicianProfilePageSerivce, authenticationService, profileLink, $timeout) {
        this.authService = authenticationService;
        this.profileLink = profileLink;

        var userId = this.authService.details.data.id;

    	this.musicContributions = angular.copy(musicContributions);
    	this.musicianRoles = angular.copy(musicianRoles);
    	this.data = angular.copy(bandData);

        this.addWriter = () => this.data.ownershipInformation.songwriters.push({
            firstName: '', lastName: '', musicianRole:''
        });
        this.selectContribution = (contribution) => { if(!contribution) this.data.contribution = null; };

        this.selectRole = (role) => { if(!role) this.data.musicianRole = null; };

        this.removeWriter = (i) => this.data.ownershipInformation.songwriters.splice(i, 1);
        this.submit = () => {
        	var res = musicianProfilePageSerivce.addBand(this.data);
        	$state.reload;
            console.log('should redirect');
            console.log('extra dank');
            $timeout(function() { $state.go(profileLink.href, { id: userId }, { reload: true })}, 500);
        };
    }
})(window.angular);