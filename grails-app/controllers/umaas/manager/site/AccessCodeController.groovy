package umaas.manager.site

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AccessCodeController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AccessCode.list(params), model:[accessCodeCount: AccessCode.count()]
    }

    def show(AccessCode accessCode) {
        respond accessCode
    }

    @Transactional
    def save(AccessCode accessCode) {
        if (accessCode == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (accessCode.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond accessCode.errors, view:'create'
            return
        }

        accessCode.save flush:true

        respond accessCode, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(AccessCode accessCode) {
        if (accessCode == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (accessCode.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond accessCode.errors, view:'edit'
            return
        }

        accessCode.save flush:true

        respond accessCode, [status: OK, view:"show"]
    }

    @Transactional
    def delete(AccessCode accessCode) {

        if (accessCode == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        accessCode.delete flush:true

        render status: NO_CONTENT
    }
}
