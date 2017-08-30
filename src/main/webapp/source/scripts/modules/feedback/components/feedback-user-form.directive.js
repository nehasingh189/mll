/**
 * Created by nehas on 7/26/2017.
 */
(function (angular) {
    'use strict';

    angular
        .module('mllApp.feedback')
        .directive('feedbackForm', feedbackForm);

    function feedbackForm() {
        return {
            restrict: 'AE',
            replace: true,
            scope: {},
            controller: 'MusicianFeedbackController',
            controllerAs: 'ctrl',
            templateUrl: 'feedback-user-form.template.html',
            bindToController: {
                userId: '='
            }
        };
    }
})(window.angular);