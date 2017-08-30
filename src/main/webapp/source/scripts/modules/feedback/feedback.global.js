 (function(angular) {
     'use strict';

     let feedbackDataUrl= '/MLL/FeedbackServlet';
     let feedbacklistUrl = '/MLL/GetFeedbacksServlet';
     let feedbackData = {
         userId: '',
         feedbackString:'',
         emailId:''
     };

     // .constant('deleteUrl', deleteUrl)

     angular
         .module('mllApp.feedback')
         .constant('feedbackDataUrl', feedbackDataUrl)
         .constant('feedbacklistUrl',feedbacklistUrl)
         .constant('feedbackData', feedbackData);

 })(window.angular);