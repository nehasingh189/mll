(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicOwnerInformationFormController', MusicOwnerInformationFormController);
    
    MusicOwnerInformationFormController.$inject = ['musicContributions', 'musicianRoles', 'authenticationService', 'musicianProfilePageSerivce'];

    function MusicOwnerInformationFormController(musicContributions, musicianRoles, authenticationService, musicianProfilePageSerivce) {

    	this.musicContributions = angular.copy(musicContributions);
    	this.authService = authenticationService;
    	this.bands = this.authService.details.data.bands;
    	//console.log("medhavi");
    	//console.log(this.bands);
    	//console.log(this.authService);
    	//console.log(musicianProfilePageSerivce);

        /**
         * Adds a new songwriter/composer
         */
        this.addWriter = () => this.data.songwriters.push({
            name: '',
            primaryPhone: '',
            secondaryPhone: '',
            primaryEmail: '',
            secondaryEmail: '',
            contribution:'',
            ownershipPercent: 0,
            pubCompany: '',
            pro: ''
        });

        this.selectContribution = (contribution) => {
            if (!contribution) {
                this.data.contribution = null;
            }
        };
        
        this.selectRole = (role) => {
            if (!role) {
                this.data.musicianRole = null;
            }
        };
        
        this.removeWriter = (i) => {
            this.data.songwriters.splice(i, 1);
        };

        this.reset = () => {
            this.onPrevious();
        };

        this.submit = () => {
            if (this.ownerForm.$invalid) {
            	this.ownerForm.$submitted = true;
            } else {
                this.onNext();
            }
        };

        /**
         * Validates that ownership percentage across all owners/composers adds up to 100
         */
        this.validateOwnershipPercentage = () => {
            const ownershipPercentages = this.data.songwriters.map((a) => { return a.ownershipPercent; });
            this.ownerForm.$validateOwnership = (100 === ownershipPercentages.reduce((a, b) => a + b, 0));
        };

        this.getBandDetails = (band) => {
            console.log("MusicOwnerInformationFormController");
            console.log(band);
            musicianProfilePageSerivce.getBandDetails(band.id)
                .then((response) => {
                    const bandDetails = response;
                    console.log(response);
                    this.data.songwriters = bandDetails;
                })
                .catch((rejection) => rejection);
        };

    }
})(window.angular);
