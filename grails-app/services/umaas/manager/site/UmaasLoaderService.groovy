package umaas.manager.site

import grails.transaction.Transactional
import umaas.manager.site.Domain
import org.springframework.beans.factory.annotation.Value


@Transactional
class UmaasLoaderService {
    @Value('${umaas.core:http://localhost:8040/umaas-core}')
  	String umaasCoreUrl;
  	@Value('${umaas.manager.access.id:0000}')
  	String accessCodeId;
  	@Value('${umaas.manager.access.code:0000}')
  	String accessCode;
  	@Value('${umaas.manager.domain.id:1111}')
  	String domainId;

    def loadDomain(Domain domain) {

    }


   def load(String url){
     ef resp = rest.get("http://grails.org/api/v1.0/plugin/acegi/")
   }
   def save(String url, def value){

   }
}
