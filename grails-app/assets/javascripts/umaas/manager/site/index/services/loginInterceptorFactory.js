//= wrapped
angular
.module("umaas.manager.site.index")
.factory('loginInterceptor', function(contextPath,
   callbackPersisterService, $q, $window, $rootScope) {
    var doLogin = function(){
       $rootScope.$emit("doLogin");
       console.log( "Emitted doLogin event");
    }

    var ret = {
        responseError: function(response) {
          console.log(response);
          if(response.status === 401){
            console.log("Responding");
            var deferred = $q.defer();
            callbackPersisterService.set(deferred, response);
            doLogin();
            console.log("Responded. Exitting");
            return deferred.promise;
          }else{
            return response;
          }
        }
    };
    return ret;
})

.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('loginInterceptor');
}])

.run(function($rootScope, $state){
  $rootScope.$on("doLogin", function(){
    console.log("Received doLogin event");
    console.log($state);
    $rootScope.previousState = $state.current.name;
    $state.go("login");
  })
})
