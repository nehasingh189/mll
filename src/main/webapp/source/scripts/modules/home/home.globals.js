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
    	.constant('musicForms', musicForms);
})(window.angular);