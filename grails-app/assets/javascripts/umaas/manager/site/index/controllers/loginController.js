//= wrapped

angular
    .module("umaas.manager.site.index")
    .controller("LoginController", LoginController);

function LoginController(callbackPersisterService, contextPath,
   $state, $http, $rootScope, $scope) {
     console.log("Login Controller initialized");
    var vm = $scope;
    vm.message = "hello";
    vm.login = function(username, password, rememberMe){
      console.log("Doing login");
      $.post( "/login/authenticate" ,  {username: username, password: password,
      'remember-me': rememberMe},
        function(resp){
          console.log(resp);
          if(resp.success){
            callbackPersisterService.accept();
            if($rootScope.previousState){
                var previousState = $rootScope.previousState;
                console.log("Going to previousState ",previousState);
                $rootScope.previousState = undefined;
                $state.go(previousState);
              }else{
                $state.go("index");
              }
          }else{
            var handled = callbackPersisterService.reject();
            alert("Invalid Username or password");
          }
       } )
       /*
      $http.post("login/authenticate",  {username: username, password: password},
    {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      }
    } ).then(function(resp){
            if(resp.data.success){
              var handled = callbackPersisterService.accept();
              if(!handled){
                if($rootScope.previousState){
                  $state.go($rootScope.previousState);
                  $rootScope.previousState = undefined;
                }else
                  $state.go("index");
              }
            }
         }, function(){
             var handled = callbackPersisterService.reject();
             if(!handled){
               $state.go("index");
             }
         })
         */
    }


}
