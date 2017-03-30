(function(angular){
    'use strict';

    let musicianUrl = '/MLL/MusicianHomePageServlet';
    let searchUrl = '/MLL/SearchServlet/?searchTitle=';
    let deleteUrl = '/MLL/DeleteAssetServlet/?assetId=';
    let editUrl = '/MLL/UpdateSongMetaDataServlet';
    let addBandUrl = '/MLL/ProfileServlet';
    let getBandDetailsUrl = '/MLL/BandServlet/?bandId=';

    // State of the music upload form
    let musicForms = {
        currentId: 0,
        submitFormId: 3,
        data: [
            {title: 'Recording Information', isActive: true},
            {title: 'Song Ownership Information', isActive: false},
            {title: 'Recording Upload', isActive: false},
            {title: 'Summary', isActive: false}
        ]
    };
    

    // Information relevant to a piece of music
    let musicData = {
        fileInformation: {name: '', file: null},
        generalInformation: {
            title: '',
            artists: [
                { name: '' }
            ],
            trackType: '',
            ownerType: '',
            contributions: [],
            primaryGenre: '',
            secondaryGenre: ''
        },
        ownershipInformation: {
            songwriters: [
                {
                    name: '',
                    primaryEmail: '',
                    primaryPhone: '',
                    secondaryPhone: '',
                    contribution: '',
                    ownershipPercent: 0,
                    musicianRole: ''
                }
            ],
            copyright: '',
            pubCompany: '',
            pro: ''
        },
        soundInformation: {
            soundOwners: [
                {
                    name: '',
                    primaryEmail: '',
                    primaryPhone: '',
                    secondaryPhone: '',
                    contribution: '',
                    ownershipPercent: 0,
                    musicianRole: ''
                }
            ]
        },
        serverInformation: {}
    };

    // Enumeration of the various types of contributions to a song
    let musicContributions = [
        'Lyrics',
        'Music',
        'Lyrics and Music'
    ];

    // The master contact information for a single band
    let bandData = {
        ownershipInformation: {
            bandName: '',
            songwriters: [{
                name: '',
                primaryEmail: '',
                primaryPhone: '',
                secondaryPhone: '',
                contribution: '',
                ownershipPercent: 0,
                musicianRole: ''
            }]
        }
    };

    // Enumeration of the different roles/positions in a band
    let musicianRoles = [
        'Vocals',
        'Guitar',
        'Bass',
        'Drums',
        'Keyboard',
        'Backup Vocals',
        'Percussion',
        'Brass',
        'Woodwinds',
        'Strings'
    ];

    let arUserInvitationBody = `Dear A&R Student,
    
        Thanks for your interest in the ML-Lab. We are looking forward to building our library of great music and we’re very excited that you will help us by inviting independent artists to participate.
        
        Below you will see the blurb that will be automatically included in your invitation for musicians to participate in the ML-Lab. As you can see, you will need to add the musician’s name in the intro and your name in the signature. You can feel free to add more information to the message if you want to personalize your invitation.
        
Here’s some information about the ML-Lab:
        
• All musicians must be independent (i.e., not signed to a record label).
• All songs must be original to the musician (i.e., no covers)
• The musician retains all rights to their music.
• Music in the library will NOT be commercially exploited.
• The music in the library will be listened to, browsed, evaluated, categorized, sorted and shared with other NU students and faculty.
• Eventually, we want to place music in movies, television shows, video games and commercials, however, we’re not even close to launching the Licensing Program, so for now, we’re just trying to gather some great music into our library for NU students to work with.
• Before we actually launch the Music Licensing Program, we’ll ask the musicians if they want to be included.
• If musicians do decide to participate in the Music Licensing Program, they will be free to withdraw at any time.

Please contact Professor Herlihy at d.herlihy@neu.edu if you have any comments or questions.

Dear MUSICIAN NAME

        I would like to invite you to participate in an exciting new music project at Northeastern University, the first beta-test phase of the launch of NU’s Media Licensing Laboratory (ML-Lab).

        I am asking you to join this effort, and upload your music onto our ML-LAB Platform so we can create a prototype music library solely for educational and research purposes. This will be a great way to introduce your music to NU students.

        We are also anxious to get your feedback about your experience with the ML-LAB Platform, so I ask that you send any feedback and bug reports promptly through the feedback window so that we can use your input to improve the process.

        Our long term goal is to establish a student-run music licensing program so students can place great independent music in movies, television shows, video games and commercials. However, we’re not even close to launching the Licensing Program, so for now, we’re just trying to gather some great music into our library for NU students to work with.

        Before we actually launch the Music Licensing Program, we’ll ask you if you want to be included. Participation in the Music Licensing Program will be voluntary and non-exclusive and you will retain all rights in your music. If you do decide to participate in the Music Licensing Program, you will be free to withdraw at any time.

        Please click on the link below to participate in the ML-Lab and upload your music into our Library. Please let me know if you have any comments or questions and don’t forget to give us your feedback about your experience with the Platform.

Thanks,  A&R INVITER’S NAME`;

    let musicianInvitationBody = `Dear   MUSICIAN NAME

        I would like to invite you to participate in an exciting new music project at Northeastern University, the first beta-test phase of the launch of NU’s Media Licensing Laboratory (ML-Lab).

        I am asking you to join this effort, and upload your music onto our ML-LAB Platform so we can create a prototype music library solely for educational and research purposes. This will be a great way to introduce your music to NU students.

        We are also anxious to get your feedback about your experience with the ML-LAB Platform, so I ask that you send any feedback and bug reports promptly through the feedback window so that we can use your input to improve the process.

        Our long term goal is to establish a student-run music licensing program so students can place great independent music in movies, television shows, video games and commercials. However, we’re not even close to launching the Licensing Program, so for now, we’re just trying to gather some great music into our library for NU students to work with.

        Before we actually launch the Music Licensing Program, we’ll ask you if you want to be included. Participation in the Music Licensing Program will be voluntary and non-exclusive and you will retain all rights in your music. If you do decide to participate in the Music Licensing Program, you will be free to withdraw at any time.

        Please click on the link below to participate in the ML-Lab and upload your music into our Library. Please let me know if you have any comments or questions and don’t forget to give us your feedback about your experience with the Platform.

Thanks,  A&R INVITER’S NAME`;

    angular
        .module('mllApp.home')
        .constant('musicianUrl', musicianUrl)
    	.constant('searchUrl', searchUrl)
    	.constant('deleteUrl', deleteUrl)
    	.constant('editUrl', editUrl)
    	.constant('addBandUrl', addBandUrl)
    	.constant('musicData', musicData)
    	.constant('bandData', bandData)
    	.constant('musicContributions', musicContributions)
    	.constant('musicianRoles', musicianRoles)
    	.constant('getBandDetailsUrl', getBandDetailsUrl)
    	.constant('musicForms', musicForms)
        .constant('arUserInvitationBody', arUserInvitationBody)
        .constant('musicianInvitationBody', musicianInvitationBody);
})(window.angular);