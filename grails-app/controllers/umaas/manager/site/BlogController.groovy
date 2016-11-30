package umaas.manager.site


import grails.rest.*
import grails.converters.*

class BlogController {
	static responseFormats = ['json', 'xml']
		def blogLoader;
    def index(String collection) {respond blogLoader.get(collection)}
}
