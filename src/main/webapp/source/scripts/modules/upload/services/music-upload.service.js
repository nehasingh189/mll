(function(angular){
    'use strict';

    angular
        .module('mllApp.upload')
        .factory('musicUploadService', musicUploadService);

    musicUploadService.$inject = ['$http', 'musicUrl'];

    function musicUploadService($http, musicUrl) {
        return {
            submitCloud: submitCloud,
            submitDirect: submitDirect
        };

        function submitCloud (data) {
        	console.log("music upload service**");
            console.log(data);
            return $http.post(musicUrl.cloud, data);
        }

        function submitDirect(data, fileProp) {
            let fd = new FormData();

            Object.keys(data).forEach((key) =>
                fd.append(key, (key === fileProp) ? data[key] : JSON.stringify(data[key])));

            console.log("music upload service");
            console.log(fd);
            return $http.post(musicUrl.direct, fd, {
                transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            });
        }
    }

})(window.angular);