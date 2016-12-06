//= wrapped
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree templates

angular.module("umaas.manager.site.index", [
    "umaas.manager.site.core",
    "ngAnimate",
    "ui.router",
    "formly",
    "ngMaterial",
    "formlyMaterial",
    "md.data.table"
])
.config(config);

function config($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('index', {
            url: "/",
            templateUrl: "/umaas/manager/site/index/index.html"
        })
        .state('login', {
            url: "/login",
            templateUrl: "/umaas/manager/site/index/login.html",
            controller: 'LoginController'
        })
        .state('documentation', {
            url: "/documentation",
            abstract: true,
            controller: 'DocumentationController',
            template: '<div layout-fill ui-view></div>'
        })
        .state('documentation.gettingStarted', {
            url: "/gettingStarted",
            templateUrl: "/umaas/manager/site/index/gettingStarted.html"
        })
        .state('documentation.references', {
            url: "/references",
            templateUrl: "/umaas/manager/site/index/references.html"
        })
        .state('documentation.tutorials', {
            url: "/tutorials",
            templateUrl: "/umaas/manager/site/index/tutorials.html"
        })
        .state('about', {
            url: "/about",
            templateUrl: "/umaas/manager/site/index/about.html"
        });

    $urlRouterProvider.otherwise('/');
}
