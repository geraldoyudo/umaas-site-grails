//= wrapped

angular
    .module("umaas.manager.site.community")
    .controller("CommunityController", CommunityController);

function CommunityController( $http, $scope) {
    var vm = $scope;
    console.log("Community controller loading");
    vm.sections = [
      {
        ref: "community.blog",
        title: "Blog"
      },
      {
        ref: "community.events",
        title: "Events"
      },
      {
        ref: "community.subscribe",
        title: "Subscribe"
      }
    ]
    var baseUrl = "/blog/";
    $http.get( baseUrl + "posts").then(function(resp){
      console.log( "Gotten posts");
      console.log(resp);
      vm.blogs = resp.data;
    });
}
