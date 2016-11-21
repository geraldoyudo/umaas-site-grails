//= wrapped
//= require /jquery/jquery
//= require /angular/angular
//= require /umaas/manager/site/core/umaas.manager.site.core
//= require /umaas/manager/site/index/umaas.manager.site.index
//= require /umaas/manager/site/accounts/umaas.manager.site.accounts
//= require /umaas/manager/site/domain/umaas.manager.site.domain
//= require /umaas/manager/site/accessCode/umaas.manager.site.accessCode

angular.module("umaas.manager.site", [
    "umaas.manager.site.core",
    "umaas.manager.site.index",
    "umaas.manager.site.accounts"
]);
