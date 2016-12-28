//= wrapped

angular
    .module("umaas.manager.site.index")
    .controller("IndexController", IndexController)
    .run(['$anchorScroll', function($anchorScroll) {
    $anchorScroll.yOffset = 50;   // always scroll by 50 extra pixels
  }]);

function IndexController(applicationDataFactory, contextPath,
   $state, $rootScope, $http, $sce, $anchorScroll, $location, $mdMedia) {
    var vm = this;
    vm.gotoAnchor = function(x) {
     var newHash = 'anchor' + x;
     if ($location.hash() !== newHash) {
       // set the $location.hash to `newHash` and
       // $anchorScroll will automatically scroll to it
       $location.hash('anchor' + x);
     } else {
       // call $anchorScroll() explicitly,
       // since $location.hash hasn't changed
       $anchorScroll();
     }
   };
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
    vm.$mdMedia = $mdMedia;
    applicationDataFactory.get().then(function(response) {
        vm.applicationData = response.data;
    });

    vm.stateExists = function(name) {
        return $state.get(name) != null;
    };

    vm.trustSrc = function(src) {
       return $sce.trustAsResourceUrl(src);
     }

    vm.staffs = [
      {photo: '/assets/umaas.png', name: 'Oyudo Gerald', designation: 'Software Developer'},
      {photo: '/assets/umaas.png', name: 'Oyudo Gerald', designation: 'Software Developer'},
      {photo: '/assets/umaas.png', name: 'Oyudo Gerald', designation: 'Software Developer'},
      {photo: '/assets/umaas.png', name: 'Oyudo Gerald', designation: 'Software Developer'},
      {photo: '/assets/umaas.png', name: 'Oyudo Gerald', designation: 'Software Developer'},
      {photo: '/assets/umaas.png', name: 'Oyudo Gerald', designation: 'Software Developer'},
      {photo: '/assets/umaas.png', name: 'Oyudo Gerald', designation: 'Software Developer'},
      {photo: '/assets/umaas.png', name: 'Oyudo Gerald', designation: 'Software Developer'},
      {photo: '/assets/umaas.png', name: 'Oyudo Gerald', designation: 'Software Developer'}
    ]
    $rootScope.$on('$stateChangeStart', 
    function(event, toState, toParams, fromState, fromParams, options){ 
       vm.showNav =false;
    })
}
