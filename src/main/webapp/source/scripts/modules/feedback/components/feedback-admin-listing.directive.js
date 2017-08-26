/**
 * Created by nehas on 7/8/2017.
 */
(function (angular){
    'use strict';

    angular
        .module('mllApp.feedback')
        .directive('mllFeedback', mllArhome);

    function mllArhome() {
        return {
            restrict: 'AE',
            scope: {},
            controller: 'ArhomeController',
            controllerAs: 'ctrl',
            templateUrl: 'feedback-admin-listing.template.html'
        };
    }
})(window.angular);