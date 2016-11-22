package umaas.manager.site

import grails.core.GrailsApplication
import grails.util.Environment
import grails.plugins.*
import org.springframework.beans.factory.annotation.Value;

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager
    @Value('${umaas.registration:http://localhost:8040/umaas-registration/app/register}')
    String umaasRegistration;
    @Value('${umaas.admin:http://localhost:8040/umaas-admin/app/admin}')
    String umaasAdministration;
    def index() {
          println "${umaasAdministration}  ${umaasRegistration}"
          [umaasRegistration: umaasRegistration, umaasAdmin: umaasAdministration]
    }
}
