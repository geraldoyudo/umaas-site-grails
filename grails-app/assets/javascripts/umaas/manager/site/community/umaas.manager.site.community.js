//= wrapped
//= require /angular/angular
//= require /umaas/manager/site/core/umaas.manager.site.core
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("umaas.manager.site.community", ["umaas.manager.site.core", "umaas.manager.site.index"])
.config(config);

function config($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('community', {
            url: "/community",
            templateUrl: "/umaas/manager/site/community/index.html",
            controller: "CommunityController as commCtrl"
        })
        .state('community.blog', {
            url: "/blog",
            templateUrl: "/umaas/manager/site/community/blog.html"
        })
        .state('community.events', {
            url: "/events",
            templateUrl: "/umaas/manager/site/community/events.html"
        })
        .state('community.subscribe', {
            url: "/subscribe",
            templateUrl: "/umaas/manager/site/community/subscribe.html"
        });
}
