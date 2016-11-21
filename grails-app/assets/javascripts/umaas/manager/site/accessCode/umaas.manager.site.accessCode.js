//= wrapped
//= require /angular/angular 
//= require /angular/angular-ui-router
//= require /angular/angular-resource
//= require /umaas/manager/site/core/umaas.manager.site.core
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("umaas.manager.site.accessCode", ["ui.router", "ngResource", "umaas.manager.site.core"]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('accessCode', {
            url: "/accessCode",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('accessCode.list', {
            url: "",
            templateUrl: "/umaas/manager/site/accessCode/list.html",
            controller: "AccessCodeListController as vm"
        })
        .state('accessCode.create', {
            url: "/create",
            templateUrl: "/umaas/manager/site/accessCode/create.html",
            controller: "AccessCodeCreateController as vm"
        })
        .state('accessCode.edit', {
            url: "/edit/:id",
            templateUrl: "/umaas/manager/site/accessCode/edit.html",
            controller: "AccessCodeEditController as vm"
        })
        .state('accessCode.show', {
            url: "/show/:id",
            templateUrl: "/umaas/manager/site/accessCode/show.html",
            controller: "AccessCodeShowController as vm"
        });
}
