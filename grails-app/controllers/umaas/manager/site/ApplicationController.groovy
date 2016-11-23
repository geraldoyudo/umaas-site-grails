package umaas.manager.site

import grails.core.GrailsApplication
import grails.util.Environment
import grails.plugins.*
import org.springframework.beans.factory.annotation.Value;

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager
    @Value('${umaas.registration}')
    String umaasRegistration;
    @Value('${umaas.admin}')
    String umaasAdministration;
    @Value('${umaas.domain.name}')
    String domain;
    @Value('${umaas.docs}')
    String docs;
    def index() {
          [umaasRegistration: umaasRegistration,
          umaasAdministration: umaasAdministration,
          domain: domain,
          docs: docs]
    }
}
