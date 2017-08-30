/**
 * Created by nehas on 7/8/2017.
 */
(function (angular) {
    'use strict';

    angular
        .module('mllApp.feedback')
        .directive('feedbackList', feedbackList);

    function feedbackList() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'AdminFeedbackController',
            controllerAs: 'ctrl',
            templateUrl: 'feedback-admin-listing.template.html',
            bindToController: {
                userId: '='
            }
        };
    }
})(window.angular);