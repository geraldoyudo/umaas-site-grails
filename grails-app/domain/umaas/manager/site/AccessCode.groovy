package umaas.manager.site

class AccessCode {
    String codeId
    long userId
    String code
    Map domains

    transient umaasLoader
    //static transients = ['code']
    static constraints = {
      code (bindable:true, nullable:true)
      codeId nullable:true
    }
    
    def onLoad(){
      print "Before Loading"
      println this.codeId;
      Map props = umaasLoader.loadAccessCode(this.codeId);
      this.properties = props;
    }

    def beforeInsert(){
      print "Before Inserting"
      Map props = umaasLoader.createAccessCode([code: this.code]);
      def segments = props._links.self.href.split('/') as Stack
      this.codeId = segments.pop();
      this.properties = props;
    }

    def beforeUpdate(){
      print "Before Updating"
      umaasLoader.saveDomain(this.codeId, [name: this.name, code: this.code]);
    }
}
