/**
 * Created by nehas on 7/8/2017.
 */
(function(angular){
    'use strict';

    let homeLink = { text: 'Home' };

    let loginLink = { text: 'Log In', href: 'login' };

    let logoutLink = { text: 'Log Out' };

    let feedbackLink = { text: 'Feedback',href: 'feedback' };

    let uploadLink = { text: 'Upload', href: 'musicianUpload' };

    let navigationLinks = [
        { text: 'People', href: 'people' },
        { text: 'Music', href: 'music' }
    ];

    angular
        .module('mllApp.arhome')
        .constant('homeLink', homeLink)
        .constant('loginLink', loginLink)
        .constant('logoutLink', logoutLink)
        .constant('feedbackLink', feedbackLink)
        .constant('uploadLink', uploadLink)
        .constant('navigationLinks', navigationLinks);
})(window.angular);