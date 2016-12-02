package umaas.manager.site

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DomainController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def umaasLoader;

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Domain.list(params), model:[domainCount: Domain.count()]
    }

    def show(Domain domain) {
        respond domain
    }

    @Transactional
    def save(Domain domain) {
        if (domain == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (domain.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond domain.errors, view:'create'
            return
        }

        domain.save flush:true

        respond domain, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Domain domain) {
        if (domain == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (domain.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond domain.errors, view:'edit'
            return
        }

        domain.save flush:true

        respond domain, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Domain domain) {

        if (domain == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        domain.delete flush:true

        render status: NO_CONTENT
    }

    def listByUser(String uid, Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Domain.where{
          userId == uid
          }.list(params), model:[domainCount: Domain.where{
            userId == uid
            }.count()]
    }

    def getDomainLimit(String domain) {
        respond umaasLoader.loadLimit(domain);
    }
}
