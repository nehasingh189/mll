(function(angular) {
    'use strict';

    angular
        .module('mllApp')
        .config(config);

    function config($stateProvider, $urlRouterProvider, $sceDelegateProvider) {
    	$sceDelegateProvider.resourceUrlWhitelist([
            'self',   // trust all resources from the same origin
            '*://128.31.22.157:8081//**'   // trust all resources from
											// `www.youtube.com`
        ]);
        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('home', {
                url: '/',
                views: {
                    left: { template: '' },
                    center: { template: ''},
                    right: { template: '' }
                }
            })
            .state('userRegistration', {
                url: '/user/registration/token/:token',
                views: {
                    left: { template: '' },
                    center: {
                        controller: 'UserRegistrationController as ctrl',
                        templateProvider: function($templateCache) {
                            return $templateCache.get('user-registration.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    token: function($state, $stateParams, $q, inviteTokenService, registrationTypes) {
                        let deferred = $q.defer();

                        inviteTokenService
                            .validateToken({ inviteType: registrationTypes.user, token: $stateParams.token })
                            .then((response) => {
                                if (response.data.isValid) deferred.resolve($stateParams.token);
                                
                                else {
                                    $state.go('home');
                                    deferred.reject();
                                }
                            });

                        return deferred.promise;
                    }
                }
            })
            .state('musicianRegistration', {
                url: '/musician/registration/token/:token',
                views: {
                    left: { template: '' },
                    center: {
                        controller: 'MusicianRegistrationFormController as ctrl',
                        templateProvider: function($templateCache) {
                            return $templateCache.get('musician-registration-form.template.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    token: function ($state, $stateParams, $q, inviteTokenService, registrationTypes) {
                        let deferred = $q.defer();

                        inviteTokenService
                            .validateToken({ inviteType: registrationTypes.musician, token: $stateParams.token })
                            .then((response) => {
                                if (response.data.isValid) deferred.resolve($stateParams.token);

                                else {
                                    $state.go('home');
                                    deferred.reject();
                                }
                            });

                        return deferred.promise;
                    }
                }
            })
            .state('feedbacklist', {
                url: '/admin/feedback',

                views: {
                    left: {
                        controller: 'SidebarController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html');
                        }
                    },
                    center: {
                        controller: 'AdminFeedbackController as ctrl',
                        templateProvider: function($templateCache) {
                            return $templateCache.get('feedback-admin-listing.template.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            /* else if (!authenticationService.details.data.permissions.browse) {
                             $state.go(authenticationService.details.data.type,
                             { id: authenticationService.details.data.id });
                             deferred.reject();
                             }*/

                            else deferred.resolve(+$stateParams.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('admin', {
                url: '/admin/profile/id/:id',
                views: {
                    left: {
                        controller: 'SidebarController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html');
                        }
                    },
                    center: {
	                        controller: 'ArhomeController as ctrl',
	                        templateProvider: function($templateCache) {
	                            return $templateCache.get('arhome.template.html');
	                             }
                    		},
                      right: { template: '' }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.browse) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve(+$stateParams.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            //NEHA
            .state('feedback', {
                url: '/musician/feedback',

                views: {
                    left: {
                        controller: 'SidebarController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html');
                        }
                    },
                    center: {
                        controller: 'MusicianFeedbackController as ctrl',
                        templateProvider: function($templateCache) {
                            return $templateCache.get('feedback-user-form.template.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                           /* else if (!authenticationService.details.data.permissions.browse) {
                               $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }*/

                            else deferred.resolve(+$stateParams.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })            //NEHA-END
            .state('arUser', {
                url: '/arUser/profile/id/:id',
                views: {
                    left: {
                        controller: 'SidebarController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html');
                        }
                    },
                    center: {                         
                    	controller: 'ARFeaturesController as model',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('ar-profile-center.view.html');
                        }
                    },
                    right: {
                    	template: ''
                    }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.browse) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve(+$stateParams.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('musician', {
                url: '/musician/profile/id/:id',
                views: {
                    left: { 
                    	controller: 'SidebarController as ctrl',
                    	templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html'); 
                    	}
                    },
                    center: {
                        controller: 'MusicianFeaturesController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('musician-profile-center.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (authenticationService.details.data.id !== +$stateParams.id &&
                                     authenticationService.details.data.permissions.upload) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve(+$stateParams.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            //new code 12/4
            .state('musicianProfile', {
                url: '/musician/dashboard/id/:id',
                views: {
                    left: { 
                    	controller: 'SidebarController as ctrl',
                    	templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html'); 
                    	}
                    },
                    center: {
                        controller: 'MusicianProfileController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('musician-profile-full-center.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                	userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                        	
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else {
                            	deferred.resolve(+$stateParams.id);
                            }
                        }, 0);
                        return deferred.promise;
                    }
                }
            })
            .state('addBand', {
                url: '/musician/band/id/:id',
                views: {
                    left: { 
                    	controller: 'SidebarController as ctrl',
                    	templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html'); 
                    	}
                    },
                    center: {
                        controller: 'BandProfileController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('band-add-profile.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                	userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                        	
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else {
                            	deferred.resolve(+$stateParams.id);
                            }
                        }, 0);
                        return deferred.promise;
                    }
                }
            })
            .state('musicianUpload', {
                url: '/musician/upload',
                views: {
                    left: { 
                    	controller: 'SidebarController as ctrl',
                    	templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html'); 
                    	}
            },
                    center: {
                        controller: 'MusicianUploadController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('musician-upload-center.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.upload) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve(+authenticationService.details.data.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('invite', {
                url: '/invite/id/:id',
                views: {
                    left: {
                        controller: 'SidebarController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html');
                        }
                    },
                    right: { template: '' },
                    center: {
                        controller: 'UserFeaturesController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('user-profile-right.view.html');
                        }
                    }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.browse) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve(+$stateParams.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
			.state('track', {
                url: '/arUser/profile/id/:id',
                views: {
                    left: { 
                    	controller: 'SidebarController as ctrl',
                    	templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html'); 
                    	}
                    },
                    center: {
                        controller: 'ARFeaturesController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('ar-profile-center.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (authenticationService.details.data.id !== +$stateParams.id &&
                                     authenticationService.details.data.permissions.upload) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve(+$stateParams.id);
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
			.state('about', {
                url: '/about',
                views: {
                    left: {
                        controller: 'SidebarController as ctrl',
                        templateProvider: function ($templateCache) {
                            return $templateCache.get('sidebar.template.html');
                        }
                    },
                   center: { template: `<div class="well well-lg aboutmll">
                       
						    <h2><i> About Media Licensing Lab</i></h2>
                            <p>
                            Welcome to the first beta-test phase of the launch of Northeastern University’s Media Licensing Laboratory (ML-Lab), a prototype library featuring music created by independent musicians. You are invited join this effort, and upload your music onto our Platform.
                            <br>
                            <br>    
                            One of our long term goals is to launch the first student run music Licensing Program in the country so students can place great independent music in all manner of media, including movies, television shows, video games and commercials.
                            <br>
                            <br>
                            Participation in the ML-Lab is non-exclusive, you retain all rights in your music, and you are free to withdraw from Licensing Program at any time.
                            <br>
                            <br>
                            We are anxious to get your feedback about your experience with the Platform, so we ask that you send any feedback and bug reports promptly so that we can improve the Platform.
                            <br>
                            <br>
                            Thanks for your participation in this exciting, one-of-a-kind initiative. 
                            </p>
                            <!--<hr>
<p><b><u>Team members:-</u></b><br>
Ashish Kumar <<a href="mailto:kumar.as@husky.neu.edu">kumar.as@husky.neu.edu</a>><br>
Dishant Shah <<a href="mailto:shah.dis@husky.neu.edu">shah.dis@husky.neu.edu</a>><br>
Meha Verma <<a href="mailto:verma.me@husky.neu.edu">verma.me@husky.neu.edu</a>><br>
Vishal Kotak <<a href="mailto:kotak.v@husky.neu.edu">kotak.v@husky.neu.edu</a>><br>
Medhavi Mahansaria <<a href="mailto:mahansaria.m@husky.neu.edu">mahansaria.m@husky.neu.edu</a>><br>
Shivani Gowrishankar <<a href="mailto:gowrishankar.s@husky.neu.edu">gowrishankar.s@husky.neu.edu</a>><br>
Naomi Joshi <<a href="mailto:joshi.nao@husky.neu.edu">joshi.nao@husky.neu.edu</a>><br>
Sai Mahanth Maddela <<a href="mailto:maddela.s@husky.neu.edu">maddela.s@husky.neu.edu</a>><br>
Satya Akhil Chowdary Kuchipudi <<a href="mailto:kuchipudi.s@husky.neu.edu">kuchipudi.s@husky.neu.edu</a>>
</p>-->
                        </div>`
                    }
                    
                },
                resolve: {
                    userId: function($state, $stateParams, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.browse) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else {
                            	deferred.resolve(+$stateParams.id);
                            }
                            
                            
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('login', {
                url: '/login',
                views: {
                    left: { template: '' },
                    center: {
                        controller: 'LoginController as ctrl',
                        templateProvider: function($templateCache) {
                            return $templateCache.get('login-central.view.html');
                        }
                    },
                    right: { template: '' }
                },
                resolve: {
                    userId: function($state, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (authenticationService.details.isAuth) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve();
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('music', {
                url: '/music',
                views: {
                    left: { template: '' },
                    center: { template:
                        `<div class="well well-lg">
                            <h4>
                                Oops... This feature is still under development. We do appreciate your patience.
                            </h4>
                        </div>`
                    },
                    right: { template: '' }
                },
                resolve: {
                    data: function($state, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.browse) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve();
                        }, 0);

                        return deferred.promise;
                    }
                }
            })
            .state('people', {
                url: '/people',
                views: {
                    left: { template: '' },
                    center: { template:
                        `<div class="well well-lg">
                            <h4>
                                Oops... This feature is still under development. We do appreciate your patience.
                            </h4>
                        </div>`
                    },
                    right: { template: '' }
                },
                resolve: {
                    data: function($state, $q, $timeout, authenticationService) {
                        let deferred = $q.defer();

                        $timeout(() => {
                            if (!authenticationService.details.isAuth) {
                                $state.go('login');
                                deferred.reject();
                            }

                            else if (!authenticationService.details.data.permissions.browse) {
                                $state.go(authenticationService.details.data.type,
                                    { id: authenticationService.details.data.id });
                                deferred.reject();
                            }

                            else deferred.resolve();
                        }, 0);

                        return deferred.promise;
                    }
                }
            });
    }
})(window.angular);
