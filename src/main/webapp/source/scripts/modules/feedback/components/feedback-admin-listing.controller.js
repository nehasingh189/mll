/**
 * Created by nehas on 7/8/2017.
 */
/**
 * Created by nehas on 7/26/2017.
 */
(function (angular) {
    'use strict';

    angular
        .module('mllApp.feedback')
        .controller('AdminFeedbackController', AdminFeedbackController);

    AdminFeedbackController.$inject = ['$state', 'userId','musicianFeedbackSerivce','feedbackData','authenticationService','$timeout'];


    function AdminFeedbackController($state, userId,musicianFeedbackSerivce,feedbackData,authenticationService,$timeout) {
        this.authService = authenticationService;
        this.sortReverse = true;
        musicianFeedbackSerivce.getFeedbacks()
            .then((response) => {
            //var songs = response
                console.log("in list controller");
                console.log(response);
            this.data = response;
        })
            .catch((rejection) => rejection);


    }
})(window.angular);