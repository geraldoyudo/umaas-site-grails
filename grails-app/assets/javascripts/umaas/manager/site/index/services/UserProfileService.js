//= wrapped

/*
    NOTE: This file is used by the create-ng-domain action.
    You can modify or extend the userProfileService but it is recommended that you not delete it.
*/

angular
    .module("umaas.manager.site.core")
    .service("userProfileService", userProfileService)
    .run(setUpListener);

function userProfileService() {
    var user;
    this.getUser = function(){
      return user;
    }
    this.setUser = function(u){
      console.log("Setting user " );
      console.log(u);
       user = u;
    }
}

function setUpListener(userProfileService, $rootScope){
  $rootScope.$on("app.newUser", function(event, user){
    console.log("New User Log on");
    userProfileService.setUser(user);
  })
}
