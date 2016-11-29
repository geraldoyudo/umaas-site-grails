//= wrapped

angular
    .module("umaas.manager.site.index")
    .controller("IndexController", IndexController);

function IndexController(applicationDataFactory, contextPath,
   $state, $rootScope, $http, $sce) {
    var vm = this;
    var getUser = function(){
      $http.get('profile').then(function(resp){
        console.log(resp);
        if(resp.data && resp.data.id){
          vm.user = resp.data;
          $rootScope.user = resp.data;
          $rootScope.$emit('app.newUser', resp.data);
        }
      });
    }
    getUser();
    $rootScope.$on('app.OnLogin', function(){
      console.log("Received login event");
      getUser();
    });
    vm.contextPath = contextPath;

    applicationDataFactory.get().then(function(response) {
        vm.applicationData = response.data;
    });

    vm.stateExists = function(name) {
        return $state.get(name) != null;
    };

    vm.trustSrc = function(src) {
       return $sce.trustAsResourceUrl(src);
     }

}
