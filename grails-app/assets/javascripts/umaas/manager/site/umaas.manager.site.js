//= wrapped
//= require /jquery/dist/jquery
//= require /angular/angular
//= require /angular-resource/angular-resource
//= require /angular-ui-router/release/angular-ui-router
//= require /angular-animate/angular-animate
//= require /angular-aria/angular-aria
//= require /angular-messages/angular-messages
//= require /angular-material/angular-material
//= require /api-check/dist/api-check
//= require /angular-formly/dist/formly
//= require /angular-formly-material/dist/formly-material
//= require /angular-material-data-table/dist/md-data-table
//= require /umaas/manager/site/core/umaas.manager.site.core
//= require /umaas/manager/site/index/umaas.manager.site.index
//= require /umaas/manager/site/accounts/umaas.manager.site.accounts
//= require /umaas/manager/site/community/umaas.manager.site.community

angular.module("umaas.manager.site", [
    "umaas.manager.site.core",
    "umaas.manager.site.index",
    "umaas.manager.site.accounts",
    "umaas.manager.site.community"
]);
