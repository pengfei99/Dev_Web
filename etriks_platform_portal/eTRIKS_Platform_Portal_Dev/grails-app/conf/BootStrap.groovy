import org.etriks.security.spring.SecRole
import org.etriks.security.spring.SecUser

class BootStrap {

    def init = { servletContext ->

        def role= new SecRole(authority: "Role_ADMIN").save(flush: true);
        def user=new  SecUser(username: "alice",enabled: true, password:'ok').save(flush:true);
    }
    def destroy = {
    }
}
