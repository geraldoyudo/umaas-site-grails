angular
    .module("umaas.manager.site.accounts")
    .controller("AccountsDialogController", AccountsDialogController);


function AccountsDialogController($mdDialog, $scope,
  Domain, AccessCode, user, FormFactory){
  $scope.message = "controller is working";
    var vm = $scope;
    vm.closeDialog = function(){
      $mdDialog.hide();
    }

  $scope.newAccessCodeModel = function(){
    return {code: "", userId: user.id};
  }
  $scope.newDomainModel = function(){
    return {code: "", name: "", userId: user.id};
  }
  $scope.createDomain = function(domain){
    Domain.save(domain).$promise.then(function(d){
      alert("Domain created successfully");
      $mdDialog.hide(d);
    });
  }
  $scope.createAccessCode = function(accessCode){
    AccessCode.save(accessCode).$promise.then(function(ac){
      alert("Access Code created successfully");
      $mdDialog.hide(ac);
    });
  }
  $scope.newDomainField = FormFactory.createDomain();
  $scope.newAccessCodeField = FormFactory.createAccessCode();
}
