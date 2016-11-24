//= wrapped
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("umaas.manager.site.accounts",
["umaas.manager.site.core", "umaas.manager.site.index"])
.config(config);

function config($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('account', {
            url: "/account",
            templateUrl: "/umaas/manager/site/accounts/index.html",
            controller: "AccountsController as acctCtrl"
        })
        .state('account.dashboard', {
            url: "/account/dashboard",
            templateUrl: "/umaas/manager/site/accounts/dashboard.html"
        })
        .state('account.domains', {
            url: "/account/domains",
            templateUrl: "/umaas/manager/site/accounts/domains.html"
        })
        .state('account.accessCodes', {
            url: "/account/accessCodes",
            templateUrl: "/umaas/manager/site/accounts/accessCodes.html"
        });
}
