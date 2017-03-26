(function(angular){
    'use strict';

    // Enumeration of the different music file formats
    let musicFormats = [
        '.mp3',
        '.wav'
    ];

    // Enumeration of the different genres of music
    let musicGenres = [
        'Alternative',
        'Blues',
        'Children\'s Music',
        'Christian & Gospel',
        'Comedy',
        'Classical',
        'Country',
        'Dance',
        'Electronic',
        'Hip - Hop / Rap',
        'Pop',
        'Jazz',
        'Latino',
        'R & B / Soul',
        'Reggae',
        'Metal',
        'Rock',
        'Singer / Songwriter',
        'Folk / Americana',
        'Funk'
    ].sort();

    // State of the music upload form
    let musicForms = {
        currentId: 0,
        submitFormId: 3,
        data: [
        	{ title: 'Recording Information', isActive: true },
            { title: 'Song Ownership Information', isActive: false },
            { title: 'Recording Upload', isActive: false },
            { title: 'Summary', isActive: false }
        ]
    };

    // All the information associated with a piece of music
    let musicData = {
        fileInformation: {name: '', file: null},
        generalInformation: {
            title: '',
            artists: [
                { name: '' }
            ],
            trackType: '',
            ownerType: '',
            contributions: [
                { type: '' }
            ],
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
        serverInformation: {}
    };

    // Maximum allowed size of a music file
    let musicSize = 10 * 1024 * 1024;

    // Enumeration of the various types of contributions to a song
    let musicContributions = [
        'Lyrics',
        'Music',
        'Lyrics and Music'
    ];

    // Enumeration of the different ownership types
    let ownerTypes = [
        'Artist',
        'Account Holder (me)'
    ];

    // Enumeration of the different track types
    let trackTypes = [
        'Vocal',
        'Instrumental',
        'Vocal/Instrumental'
    ];

    // Enumeration of the different instruments for recording contributions
    let contributionTypes = [
        'Acoustic Bass',
        'Acoustic Guitar',
        'Acoustic Piano',
        'Acoustic Strings',
        'Ambient Sounds',
        'Background Vocals',
        'Banjo',
        'Brass',
        'Classical Instruments',
        'Electric Bass',
        'Electric Guitar',
        'Electric Keyboards',
        'Exotic Instrumentation',
        'Horn(s)',
        'Industrial Sounds',
        'Organ',
        'Percussion',
        'Rhythmic Loops',
        'Samples',
        'Synthesizers',
        'Vocal Female',
        'Vocal Harmonies',
        'Vocal Male',
        'Woodwinds'
    ];

    // HTTP endpoints for direct-file and cloud uploads
    let musicUrl = {
        direct: '/MLL/SubmissionServlet',
        cloud: '/MLL/SubmissionServlet'
    };

    angular
        .module('mllApp.upload')
        .constant('contributionTypes', contributionTypes)
        .constant('musicFormats', musicFormats)
        .constant('musicGenres', musicGenres)
        .constant('musicForms', musicForms)
        .constant('musicData', musicData)
        .constant('musicSize', musicSize)
        .constant('musicContributions', musicContributions)
        .constant('musicUrl', musicUrl)
        .constant('trackTypes', trackTypes)
        .constant('ownerTypes', ownerTypes);
})(window.angular);