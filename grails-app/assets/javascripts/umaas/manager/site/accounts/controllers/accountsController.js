//= wrapped

angular
    .module("umaas.manager.site.accounts")
    .controller("AccountsController", AccountsController);

function AccountsController(Domain, AccessCode,
  $scope, $rootScope, userProfileService, $mdDialog, $mdSidenav) {
  $scope.user = userProfileService.getUser();
  function getDomainsByUser(){
    Domain.listByUser({uid: $scope.user.id}).$promise.then(function(domains){
        console.log(domains);
        if(domains.length){
          $scope.domains = domains;
        }
          console.log($scope.domains);
          $scope.refreshLimits();
    }, function(error){
      console.log(error);
    });
  }

  function getAccessCodesByUser(){
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
  var initialize = function(){
    $scope.domains = [];
    $scope.accessCodes =[];
    $scope.data = {};
    $scope.data.createDomainView = false;
    $scope.data.createAccessCodeView = false;
    getDomainsByUser();
    getAccessCodesByUser();

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

  $scope.saveDomain = function(domain){
      $scope.indexCtrl.running = true;
    Domain.update({id:domain.id}, domain).$promise.then(function(){
        $scope.indexCtrl.running = false;
      alert("Update Successful");
    });
  }
  $scope.saveAccessCode = function(accessCode){
    $scope.indexCtrl.running = true;
    AccessCode.update({id:accessCode.id}, accessCode).$promise.then(function(){
      $scope.indexCtrl.running = false;
      alert("Update Successful");
    });
  }

  $scope.getDomainName = function(domainId){
    for(var i=0; i<$scope.domains.length; ++i){
      if($scope.domains[i].domainId === domainId){
        return $scope.domains[i].name;
      }
    }
    throw "No domain found with id";
  }

  $scope.refreshLimits = function(){
    console.log("Refreshing limits")
    console.log($scope.domains);
    $scope.domains.forEach(function(domain){
        Domain.limit({domain: domain.domainId}).$promise.then(function(limit){
          domain.limit = limit;
        })
        Domain.userLimit({domain: domain.domainId}).$promise.then(function(limit){
          domain.userLimit = limit;
        })
    })
  }

  $scope.newDomainDialog = function($event){
    $mdDialog.show({
      parent: angular.element(document.body),
      targetEvent: $event,
      templateUrl: "/umaas/manager/site/accounts/newDomain.html",
      controller: "AccountsDialogController",
      locals: {
           user: $scope.user
         }
    }).then(function(domain){
        $scope.domains.push(domain);
    });
  }

  $scope.newAccessCodeDialog = function($event){
    $mdDialog.show({
      parent: angular.element(document.body),
      targetEvent: $event,
      templateUrl: "/umaas/manager/site/accounts/newAccessCode.html",
      controller: "AccountsDialogController",
      locals: {
           user: $scope.user
         }
    }).then(function(code){
        $scope.accessCodes.push(code);
    });
  }
 $scope.sections = [
      {
        ref: "account.dashboard",
        title: "Dashboard"
      },
      {
        ref: "account.domains",
        title: "Domains"
      },
      {
        ref: "account.accessCodes",
        title: "Access Codes"
      }
    ]
  $scope.$on('$stateChangeStart', 
  function(event, toState, toParams, fromState, fromParams, options){ 
    console.log(toState);
    if( $mdSidenav("left").isOpen()){
       $mdSidenav("left").close()
    }
      if(toState.name === "account.dashboard"){
        $scope.refreshLimits();
      }
  })
   $scope.toggleSidenav = function(menuId) {
    $mdSidenav(menuId).toggle();
  };
}
