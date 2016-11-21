//= wrapped

angular
    .module("umaas.manager.site.accounts")
    .service("FormFactory", formFactory);

function formFactory (){
  var self = this;

  self.createDomain = function (){
    return [
      {
          "type": "input",
          "key": "name",
          "templateOptions": {
            "type": "text",
            "placeholder": "Name",
            "label": "Name"
          }
        },
        {
            "type": "input",
            "key": "code",
            "templateOptions": {
              "type": "text",
              "placeholder": "Code",
              "label": "Code"
            }
          }
    ];
  }
}
