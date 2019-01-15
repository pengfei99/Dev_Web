package org.spring.auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='name')
@ToString(includes='name', includeNames=true, includePackage=false)
class SpringRoleGroup implements Serializable {

	private static final long serialVersionUID = 1

	String name

	Set<SpringRole> getAuthorities() {
		SpringRoleGroupSpringRole.findAllBySpringRoleGroup(this)*.springRole
	}

	static constraints = {
		name blank: false, unique: true
	}

	static mapping = {
		cache true
	}
}
