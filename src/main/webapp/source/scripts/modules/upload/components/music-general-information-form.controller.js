(function (angular) {
    'use strict';

    angular
        .module('mllApp.upload')
        .controller('MusicGeneralInformationFormController', MusicGeneralInformationFormController);

    MusicGeneralInformationFormController.$inject = ['musicGenres', 'ownerTypes', 'trackTypes'];

    function MusicGeneralInformationFormController(musicGenres, ownerTypes, trackTypes) {

        this.genres = angular.copy(musicGenres);
        this.ownerTypes = angular.copy(ownerTypes);
        this.trackTypes = angular.copy(trackTypes);

        this.addArtist = () => this.data.artists.push({ name: '' });

        this.removeArtist = (i) => this.data.artists.splice(i, 1);

        this.selectGenre = (genre) => { if(!genre) this.data.secondaryGenre = null; };

        this.submit = () => {
            if (this.generalForm.$invalid) this.generalForm.$submitted = true;
            else this.onNext();
        };

        this.reset = () => this.onPrevious();

    }
})(window.angular);