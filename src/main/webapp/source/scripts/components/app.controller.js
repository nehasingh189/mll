(function (angular) {
    'use strict';

    angular
        .module('mllApp')
        .controller('ApplicationController', ApplicationController);

    function ApplicationController() {

        this.getYear = () => {
            const d = new Date();
            return d.getFullYear();
        }

    }
})(window.angular);
