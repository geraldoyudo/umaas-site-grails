//= wrapped

angular
    .module("umaas.manager.site.index")
    .controller("DocumentationController", DocumentationController);

function DocumentationController( $http, $scope) {
    var vm = $scope;
    var baseUrl = "/blog/";
    $http.get( baseUrl + "tutorials").then(function(resp){
      console.log( "Gotten tutorials");
      console.log(resp);
      vm.tutorials = resp.data;
    });
    $http.get( baseUrl + "references").then(function(resp){
      console.log(resp);
      vm.references = resp.data;
    });
    $http.get( baseUrl + "guides").then(function(resp){
      console.log(resp);
      vm.guides = resp.data;
    });

    vm.fullUrl = function(url){
      if(!url.startsWith("http")){
        url = vm.indexCtrl.applicationData.blog + "/" +  url;
      }
      return url;
    }

}
