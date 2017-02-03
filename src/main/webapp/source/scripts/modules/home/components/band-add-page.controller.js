(function (angular) {
    'use strict';

    angular
        .module('mllApp.home')
        .controller('BandAddPageController', BandAddPageController);
    
    BandAddPageController.$inject = ['$state', 'musicContributions', 'musicianRoles', 'bandData', 'musicianProfilePageSerivce'];

    function BandAddPageController($state, musicContributions, musicianRoles, bandData, musicianProfilePageSerivce) {

    	this.musicContributions = angular.copy(musicContributions);
    	this.musicianRoles = angular.copy(musicianRoles);
    	this.data = angular.copy(bandData);
    	
        this.addWriter = () => this.data.ownershipInformation.songwriters.push({
            name: '', primaryPhone: '', secondaryPhone: '', primaryEmail: '', secondaryEmail: '', contribution:'', ownershipPercent: 0, musicianRole:''
        });

        this.selectContribution = (contribution) => { if(!contribution) this.data.contribution = null; };
        
        this.selectRole = (role) => { if(!role) this.data.musicianRole = null; };
        
        this.removeWriter = (i) => this.data.songwriters.splice(i, 1);
        this.submit = () => {
        	var res = musicianProfilePageSerivce.addBand(this.data);
        	$state.reload;
        };
    }
})(window.angular);