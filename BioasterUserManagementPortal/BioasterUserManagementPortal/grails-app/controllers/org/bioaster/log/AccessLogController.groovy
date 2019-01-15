package org.bioaster.log

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AccessLogController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AccessLog.list(params), model:[accessLogCount: AccessLog.count()]
    }

    def show(AccessLog accessLog) {
        respond accessLog
    }


    @Transactional
    def save(AccessLog accessLog) {
        if (accessLog == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (accessLog.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond accessLog.errors, view:'create'
            return
        }

        accessLog.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'accessLog.label', default: 'AccessLog'), accessLog.id])
                redirect accessLog
            }
            '*' { respond accessLog, [status: CREATED] }
        }
    }

   /* def edit(AccessLog accessLog) {
        respond accessLog
    }

    @Transactional
    def update(AccessLog accessLog) {
        if (accessLog == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (accessLog.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond accessLog.errors, view:'edit'
            return
        }

        accessLog.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'accessLog.label', default: 'AccessLog'), accessLog.id])
                redirect accessLog
            }
            '*'{ respond accessLog, [status: OK] }
        }
    }

    @Transactional
    def delete(AccessLog accessLog) {

        if (accessLog == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        accessLog.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'accessLog.label', default: 'AccessLog'), accessLog.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }*/

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'accessLog.label', default: 'AccessLog'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
