package umaas.manager.site


import grails.rest.*
import grails.converters.*

class ProfileController {
	static responseFormats = ['json', 'xml']
		def springSecurityService;
    def index() {respond springSecurityService.authentication.principal }
}
