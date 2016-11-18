//= wrapped

angular
    .module("umaas.manager.site.index")
    .service("callbackPersisterService", callbackPersisterService);

function callbackPersisterService() {
    var deferred;
    var argument;
    this.set = function(deferredRef, argRef){
      deferred = deferredRef;
      argument = argRef;
    }
    this.accept = function(){
      if(deferred){
        deferred.resolve(argument);
        deferred = undefined;
        return true;
      }
      return false;
    }
    this.reject = function(){
      if(deferred){
        deferred.reject(argument);
        deferred = undefined;
        return true;
      }
      return false;
    }
}
