package umaas.manager.site

class Domain {
    String domainId
    String userId
    String name
    String code
    transient umaasLoader

  // static transients = ['name', 'code']
    static constraints = {
      name (bindable:true, nullable:true)
      code (bindable:true, nullable:true)
      domainId nullable:true
    }



    def afterLoad(){
      print "Before Loading"
      print this.domainId;
      print domainId;
      Map props = umaasLoader.loadDomain(this.domainId);
      println props
      this.properties = props;
      println "${code}  ${name}"
    }

    def beforeInsert(){
      println "Before Inserting"
      println "${umaasLoader.accessCodeId}  ${umaasLoader.accessCode}"
      Map props = umaasLoader.createDomain([name: this.name, code: this.code]);
      def segments = props._links.self.href.split('/') as Stack
      this.domainId = segments.pop();
      println props._links.self.href;
      println this.domainId;
      this.properties = props;
      println "${code}  ${name}"
    }

    def beforeUpdate(){
      print "Before Updating"
      this.properties = umaasLoader.saveDomain(this.domainId, [name: this.name, code: this.code]);
    }
}
