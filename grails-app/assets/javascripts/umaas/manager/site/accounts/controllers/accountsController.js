//= wrapped

angular
    .module("umaas.manager.site.accounts")
    .controller("AccountsController", AccountsController);

function AccountsController(Domain, AccessCode,
  $scope, $rootScope, userProfileService) {
  $scope.user = userProfileService.getUser();
  var initialize = function(){
    $scope.domains = [];
    $scope.accessCodes =[];
    Domain.listByUser({uid: $scope.user.id}).$promise.then(function(domains){
        console.log(domains);
        if(domains.length){
          $scope.domains = domains;
        }
          console.log($scope.domains);
    }, function(error){
      console.log(error);
    });

    AccessCode.listByUser({uid: $scope.user.id}).$promise.then(function(codes){
      console.log(codes);
      if(codes.length){
        $scope.accessCodes = codes;
      }
        console.log($scope.accessCodes);
    }, function(error){
        console.log(error);
    });
  }
  var reloadUser = function(event, user){
    console.log("User change");
    $scope.user = user;
    console.log($scope.user);
    initialize();
  };
  if($scope.user)
    initialize();
  $rootScope.$on('app.newUser', reloadUser);
  console.log("Initializing accounts controller");

}
