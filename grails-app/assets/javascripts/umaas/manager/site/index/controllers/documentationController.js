//= wrapped

angular
    .module("umaas.manager.site.index")
    .controller("DocumentationController", DocumentationController);

function DocumentationController( contextPath, $http, $scope) {
    var vm = $scope;

    vm.tutorials = [
      {title: "Sample tutorial", url: "http://google.com",
    logo: "http://logo.com"}
    ]

    vm.references = [
      {title: "Sample Reference", url: "http://google.com",
    logo: "http://logo.com", platform: "java"}
    ]

    vm.guides = [
      {title: "title", url: "http://google.com",
    logo: "http://logo.com", platform: "spring boot"}
    ]
}
