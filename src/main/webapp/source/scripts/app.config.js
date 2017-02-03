(function(angular) {
    'use strict';

    angular
        .module('mllApp')
        .config(config);

    function config($stateProvider, $urlRouterProvider, $sceDelegateProvider) {
    	$sceDelegateProvider.resourceUrlWhitelist([
            'self',   // trust all resources from the same origin
            '*://35.163.135.77:8081//**'   // trust all resources from
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
                        controller: 'MusicianRegistrationController as ctrl',
                        templateProvider: function($templateCache) {
                            return $templateCache.get('musician-registration.view.html');
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
            .state('user', {
                url: '/user/profile/id/:id',
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
                   center: { template:
                        `<div class="well well-lg aboutmll">
                       
						    <h2><i> About Media Licensing Lab</i></h2>
                            <p>
                               The Media Licensing Lab (MLL) is a Northeastern University initiative to establish the first student-run music licensing program in the United States. The MLL links musicians, student A&R representatives, the Northeastern community, and the music industry.
Selected musicians are invited by A&R student representatives to upload their music into the MLL platform. Students evaluate the music and license the best songs into tv shows, film, video games, and other media.
The MLL provides students with real-world music licensing experience, exposes musicians to a wider audience, and enables music licensees to discover the perfect piece of music for their project, at a fraction of the cost of typical content libraries.
                            </p><hr>
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
</p>
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
