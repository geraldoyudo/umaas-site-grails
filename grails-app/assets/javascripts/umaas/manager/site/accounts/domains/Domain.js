//= wrapped
//= require /angular/angular-resource

angular
    .module("umaas.manager.site.domain")
    .factory("Domain", Domain);

function Domain($resource) {
    var Domain = $resource(
        "domain/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true},
         "get": {method: 'GET'}}
    );

    Domain.list = Domain.query;

    Domain.prototype.toString = function() {
        return 'umaas.manager.site.Domain : ' + (this.id ? this.id : '(unsaved)');
    };

    return Domain;
}
