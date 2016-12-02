//= wrapped
//= require /angular/angular-resource

angular
    .module("umaas.manager.site.accounts")
    .factory("Domain", Domain);

function Domain($resource) {
    var Domain = $resource(
        "domain/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true},
         "get": {method: 'GET'},
          "listByUser": {method: 'GET',
          url:"domain/listByUser?uid=:uid", isArray: true},
        "limit": {method: 'GET',
          url:"domain/:domain/limit"}}
    );

    Domain.list = Domain.query;

    Domain.prototype.toString = function() {
        return 'umaas.manager.site.Domain : ' + (this.id ? this.id : '(unsaved)');
    };

    return Domain;
}
