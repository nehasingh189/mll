/**
 * Created by nehas on 7/26/2017.
 */
(function (angular) {
    'use strict';
    
    angular
        .module('mllApp.feedback')
        .controller('MusicianFeedbackController', MusicianFeedbackController);

    MusicianFeedbackController.$inject = ['$state', 'userId','musicianFeedbackSerivce','feedbackData','authenticationService','$timeout'];

   // MusicianFeedbackController.$inject = ['userId'];

    // function MusicianUploadController(userId) {
    //     this.userId = userId;
    // }
    function MusicianFeedbackController($state, userId,musicianFeedbackSerivce,feedbackData,authenticationService,$timeout) {
        this.authService = authenticationService;
        var userId = this.authService.details.data.id;
        this.data = angular.copy(feedbackData);

        console.log(this.data);
        console.log(userId);
        this.data.userId = userId;
        this.data.emailId = this.authService.details.data.emailid;
        //console.log(this.authService);

        this.submit = () => {
           var res = musicianFeedbackSerivce.addFeedback(this.data);
            $state.reload();
            console.log(this.data);
            //console.log('extra dank');
            //$timeout(function() { $state.go(profileLink.href, { id: userId }, { reload: true })}, 500);
        };

    }
})(window.angular);