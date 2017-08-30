/**
 * Created by nehas on 7/27/2017.
 */

(function(angular) {
    'use strict';

    angular
        .module('mllApp.feedback')
        .factory('musicianFeedbackSerivce', musicianFeedbackSerivce);

    musicianFeedbackSerivce.$inject = ['$http', 'feedbackDataUrl','feedbacklistUrl'];


    function musicianFeedbackSerivce($http, feedbackDataUrl, feedbacklistUrl) {
        return {
            addFeedback: addFeedback,
            getFeedbacks: getFeedbacks
        };


        function addFeedback(data) {
            console.log("in add feedback in service");
            console.log("feedback data is "+feedbackDataUrl);
            console.log(data);
            return $http.post(feedbackDataUrl, data)
                .then((response) => {
                    console.log("after servlets hit");
                    console.log(response);
                    return response.data;
                })
                .catch((rejection) => rejection);
        }


        function getFeedbacks(){
            return $http.post(feedbacklistUrl)
                .then((response) => {
                    //console.log("after servlets hit");
                    console.log(response);
                    return response.data;
                })
                .catch((rejection) => rejection);
        }
        /*
         return $http.post(bandUrl)
         .then((response) => {
         //console.log("after servlets hit");
         //	console.log(response);
         return response.data;
         })
         .catch((rejection) => rejection);
         */

    }
})(window.angular);