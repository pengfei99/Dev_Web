package org.bioaster.intercepter


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoggedInInterceptor)
class LoggedInInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test loggedIn interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"loggedIn")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
