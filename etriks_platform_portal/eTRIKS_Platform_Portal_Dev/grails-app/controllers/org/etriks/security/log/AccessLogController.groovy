package org.etriks.security.log

import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.security.authorization.PolicyEngine

import java.text.DateFormat
import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AccessLogController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def userRoles=null;
        def unknowUser=false;
        try{
            userRoles=session.userDetails.getRole();}
        catch (NullPointerException e)
        {
          unknowUser=true
        }
        if(unknowUser==true){
            redirect(controller:"accessControlException",action:"notLoggedIn")
        }
        else{

        PolicyEngine pe=new PolicyEngine();

if (pe.checkRoles("Role_Admin",userRoles)){
   //access log view for admin
     respond AccessLog.list(params), model:[accessLogInstanceCount: AccessLog.count()]
}
 else if(pe.checkRoles("Role_AbiriskAdmin",userRoles)){
    //access log view for abirisk admin
    def acclist=AccessLog.list(params)
    def accessLogInstanceList=new ArrayList<AccessLog>();
    def  int size;
    def  iterator= acclist.iterator()
    while(iterator.hasNext()){
        def acclog= iterator.next();
        if (acclog.getProjectId()==ProjectId.abirisk){
            accessLogInstanceList.add(acclog)
        }

    }
    size = accessLogInstanceList.size();
    render (view:"index",model:[accessLogInstanceCount: size,accessLogInstanceList:accessLogInstanceList])
}
else if(pe.checkRoles("Role_OncoTrackAdmin",userRoles)){
    //access log view for oncotrack admin
    def acclist=AccessLog.list(params)
    def accessLogInstanceList=new ArrayList<AccessLog>();
    def  int size;
    def  iterator= acclist.iterator()
    while(iterator.hasNext()){
        def acclog= iterator.next();
        if (acclog.getProjectId()==ProjectId.oncoTrack){
            accessLogInstanceList.add(acclog)
        }
    }
    size = accessLogInstanceList.size();
    render (view:"index",model:[accessLogInstanceCount: size,accessLogInstanceList:accessLogInstanceList])
}
else if(pe.checkRoles("Role_BioasterAdmin",userRoles)){
    //access log view for oncotrack admin
    def acclist=AccessLog.list(params)
    def accessLogInstanceList=new ArrayList<AccessLog>();
    def  int size;
    def  iterator= acclist.iterator()
    while(iterator.hasNext()){
        def acclog= iterator.next();
        if (acclog.getProjectId()==ProjectId.bioaster){
            accessLogInstanceList.add(acclog)
        }
    }
    size = accessLogInstanceList.size();
    render (view:"index",model:[accessLogInstanceCount: size,accessLogInstanceList:accessLogInstanceList])
}

else{
    //error, unable to recognize user role
    redirect(controller:"accessControlException",action:"accessDeny")
}

    }
    }

    def show(AccessLog accessLogInstance) {
        respond accessLogInstance
    }

   /* def create() {
        respond new AccessLog(params)
    }

    @Transactional
    def save(AccessLog accessLogInstance) {
        if (accessLogInstance == null) {
            notFound()
            return
        }

        if (accessLogInstance.hasErrors()) {
            respond accessLogInstance.errors, view:'create'
            return
        }

        accessLogInstance.save flush:true


       *//* saveLog("test3","p.liu",ProjectId.etriks,LogType.Error )*//*

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'accessLogInstance.label', default: 'AccessLog'), accessLogInstance.id])
                redirect accessLogInstance
            }
            '*' { respond accessLogInstance, [status: CREATED] }
        }
    }



    def edit(AccessLog accessLogInstance) {
        respond accessLogInstance
    }

    @Transactional
    def update(AccessLog accessLogInstance) {
        if (accessLogInstance == null) {
            notFound()
            return
        }

        if (accessLogInstance.hasErrors()) {
            respond accessLogInstance.errors, view:'edit'
            return
        }

        accessLogInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AccessLog.label', default: 'AccessLog'), accessLogInstance.id])
                redirect accessLogInstance
            }
            '*'{ respond accessLogInstance, [status: OK] }
        }
    }
*/
   /* @Transactional
    def delete(AccessLog accessLogInstance) {

        if (accessLogInstance == null) {
            notFound()
            return
        }

        accessLogInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AccessLog.label', default: 'AccessLog'), accessLogInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
*/

    /*This method can export the data base log to a file for long term storage*/
   def exportLogToFile(){

   }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'accessLogInstance.label', default: 'AccessLog'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
