package org.bioaster.intercepter


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AccessControlInterceptor)
class AccessControlInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test accessControl interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"accessControl")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
