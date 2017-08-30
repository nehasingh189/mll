(function(angular){
    'use strict';

    let homeLink = { text: 'Home' };

    let loginLink = { text: 'Log In', href: 'login' };

    let logoutLink = { text: 'Log Out' };

    let feedbackLink = { text: 'Feedback',href: 'feedback' };

    let feedbackListLink = { text: 'FeedbackListLink',href: 'feedbacklist' };

    let uploadLink = { text: 'Upload', href: 'musicianUpload' };

    let navigationLinks = [
        { text: 'People', href: 'people' },
        { text: 'Music', href: 'music' }
    ];

    angular
        .module('mllApp.header')
        .constant('homeLink', homeLink)
        .constant('loginLink', loginLink)
        .constant('feedbackLink', feedbackLink)
        .constant('feedbackListLink',feedbackListLink)
        .constant('logoutLink', logoutLink)
        .constant('uploadLink', uploadLink)
        .constant('navigationLinks', navigationLinks);
})(window.angular);