(function(angular){
	'use strict';
    angular
        .module("mllApp.arhome")
        .controller("SinglePlaylistController", SinglePlaylistController);
    
    SinglePlaylistController.$inject = ['arHomeSerivce', 'authenticationService', 'id','name', 'deleteSong', '$state', '$uibModalInstance'];

	function SinglePlaylistController(arHomeSerivce, authenticationService, id, name,deleteSong, $state, $uibModalInstance) {
       this.authService = authenticationService;
       var model = this;
       var userId = model.authService.details.data.id;
       model.sortType = 'track';
       model.name= name;
       model.sortReverse = false;
       model.deleteSongBoolean = deleteSong;
       getSongsInPlaylist();       
       
       model.deleteSong = function(assetId){
    	   arHomeSerivce.deleteSong(id,assetId).success(function(response){
    		   if(response.songs.length > 0){
            	   model.tracks = response.songs;
    			   model.playlistExist = true;    			   
    		   }else{
    			   model.playlistExist = false;
    		   }
    	   })
       }
       
       
		model.track = function(){
            $state.go("track", { id: userId});
            $uibModalInstance.dismiss('cancel');
		};		
		
       function getSongsInPlaylist(){
    	   arHomeSerivce.getSongsInPlaylist(id)
           .then((response) => {

    		   if(response.data.songs.length > 0){
            	   model.tracks = response.data.songs;
    			   model.playlistExist = true;
    		   }else{
    			   model.playlistExist = false;
    		   }
           })
           .catch((rejection) => rejection);
        }
       }
})(window.angular);
