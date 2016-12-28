//= wrapped

angular
    .module("umaas.manager.site.community")
    .controller("CommunityController", CommunityController);

function CommunityController( $http, $scope, $rootScope, $mdSidenav) {
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
    vm.socials = [
      {url:"/assets/logos/facebook.jpg", name:"facebook", href:"http://facebook.com/umaas" },
      {url:"/assets/logos/instagram.jpg", name:"instagram", href:"http://instagram.com/umaas" },
      {url:"/assets/logos/twitter.png", name:"twitter", href:"http://twitter.com/umaas" }

    ]
    var baseUrl = "/blog/";
    $http.get( baseUrl + "posts").then(function(resp){
      console.log( "Gotten posts");
      console.log(resp);
      vm.blogs = resp.data;
    });

    $http.get( baseUrl + "events").then(function(resp){
      console.log( "Gotten events");
      console.log(resp);
      vm.events = resp.data;
    });
    vm.toggleSidenav = function(menuId) {
    $mdSidenav(menuId).toggle();
  };
}
