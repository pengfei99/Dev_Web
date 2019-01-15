package org.spring.auth

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class SpringUserSpringRole implements Serializable {

	private static final long serialVersionUID = 1

	SpringUser springUser
	SpringRole springRole

	@Override
	boolean equals(other) {
		if (other instanceof SpringUserSpringRole) {
			other.springUserId == springUser?.id && other.springRoleId == springRole?.id
		}
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (springUser) builder.append(springUser.id)
		if (springRole) builder.append(springRole.id)
		builder.toHashCode()
	}

	static SpringUserSpringRole get(long springUserId, long springRoleId) {
		criteriaFor(springUserId, springRoleId).get()
	}

	static boolean exists(long springUserId, long springRoleId) {
		criteriaFor(springUserId, springRoleId).count()
	}

	private static DetachedCriteria criteriaFor(long springUserId, long springRoleId) {
		SpringUserSpringRole.where {
			springUser == SpringUser.load(springUserId) &&
			springRole == SpringRole.load(springRoleId)
		}
	}

	static SpringUserSpringRole create(SpringUser springUser, SpringRole springRole) {
		def instance = new SpringUserSpringRole(springUser: springUser, springRole: springRole)
		instance.save()
		instance
	}

	static boolean remove(SpringUser u, SpringRole r) {
		if (u != null && r != null) {
			SpringUserSpringRole.where { springUser == u && springRole == r }.deleteAll()
		}
	}

	static int removeAll(SpringUser u) {
		u == null ? 0 : SpringUserSpringRole.where { springUser == u }.deleteAll()
	}

	static int removeAll(SpringRole r) {
		r == null ? 0 : SpringUserSpringRole.where { springRole == r }.deleteAll()
	}

	static constraints = {
		springRole validator: { SpringRole r, SpringUserSpringRole ur ->
			if (ur.springUser?.id) {
				SpringUserSpringRole.withNewSession {
					if (SpringUserSpringRole.exists(ur.springUser.id, r.id)) {
						return ['userRole.exists']
					}
				}
			}
		}
	}

	static mapping = {
		id composite: ['springUser', 'springRole']
		version false
	}
}
