//= wrapped

angular
    .module("umaas.manager.site.accounts")
    .controller("AccountsController", AccountsController);

function AccountsController(Domain, AccessCode,
  $scope, $rootScope, userProfileService, FormFactory) {
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

  $scope.newDomainField = FormFactory.createDomain();
  $scope.newAccessCodeField = FormFactory.createAccessCode();
  $scope.saveDomain = function(domain){
    Domain.update({id:domain.id}, domain).$promise.then(function(){
      alert("Update Successful");
    });
  }
  $scope.saveAccessCode = function(accessCode){
    AccessCode.update({id:accessCode.id}, accessCode).$promise.then(function(){
      alert("Update Successful");
    });
  }
  $scope.createDomain = function(domain){
    Domain.save(domain).$promise.then(function(d){
      alert("Domain created successfully");
      $scope.domains.push(d);
    });
  }
  $scope.createAccessCode = function(accessCode){
    AccessCode.save(accessCode).$promise.then(function(ac){
      alert("Access Code created successfully");
      $scope.accessCodes.push(ac);
    });
  }

  $scope.newAccessCodeModel = function(){
    return {code: "", userId: $scope.user.id};
  }
  $scope.newDomainModel = function(){
    return {code: "", name: "", userId: $scope.user.id};
  }
  $scope.getDomainName = function(domainId){
    for(var i=0; i<$scope.domains.length; ++i){
      if($scope.domains[i].domainId === domainId){
        return $scope.domains[i].name;
      }
    }
    throw "No domain found with id";
  }
}
