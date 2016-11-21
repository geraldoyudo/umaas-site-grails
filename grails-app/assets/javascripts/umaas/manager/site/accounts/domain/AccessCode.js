//= wrapped
//= require /angular/angular-resource

angular
    .module("umaas.manager.site.accounts")
    .factory("AccessCode", AccessCode);

function AccessCode($resource) {
    var AccessCode = $resource(
        "accessCode/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true},
         "get": {method: 'GET'},
          "listByUser": {method: 'GET',
          url:"accessCode/listByUser?uid=:uid", isArray: true}}
    );

    AccessCode.list = AccessCode.query;

    AccessCode.prototype.toString = function() {
        return 'umaas.manager.site.AccessCode : ' + (this.id ? this.id : '(unsaved)');
    };

    return AccessCode;
}
