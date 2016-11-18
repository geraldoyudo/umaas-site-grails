//= wrapped
//= require /angular/angular
//= require /angular/ui-bootstrap-tpls
//= require /angular/angular-ui-router
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree templates

angular.module("umaas.manager.site.index", [
    "umaas.manager.site.core",
    "ui.bootstrap.dropdown",
    "ui.bootstrap.collapse",
    "ui.router"
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
        });

    $urlRouterProvider.otherwise('/');
}
