package org.spring.auth

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class SpringUserSpringRoleGroup implements Serializable {

	private static final long serialVersionUID = 1

	SpringUser springUser
	SpringRoleGroup springRoleGroup

	@Override
	boolean equals(other) {
		if (other instanceof SpringUserSpringRoleGroup) {
			other.springUserId == springUser?.id && other.springRoleGroupId == springRoleGroup?.id
		}
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (springUser) builder.append(springUser.id)
		if (springRoleGroup) builder.append(springRoleGroup.id)
		builder.toHashCode()
	}

	static SpringUserSpringRoleGroup get(long springUserId, long springRoleGroupId) {
		criteriaFor(springUserId, springRoleGroupId).get()
	}

	static boolean exists(long springUserId, long springRoleGroupId) {
		criteriaFor(springUserId, springRoleGroupId).count()
	}

	private static DetachedCriteria criteriaFor(long springUserId, long springRoleGroupId) {
		SpringUserSpringRoleGroup.where {
			springUser == SpringUser.load(springUserId) &&
			springRoleGroup == SpringRoleGroup.load(springRoleGroupId)
		}
	}

	static SpringUserSpringRoleGroup create(SpringUser springUser, SpringRoleGroup springRoleGroup) {
		def instance = new SpringUserSpringRoleGroup(springUser: springUser, springRoleGroup: springRoleGroup)
		instance.save()
		instance
	}

	static boolean remove(SpringUser u, SpringRoleGroup rg) {
		if (u != null && rg != null) {
			SpringUserSpringRoleGroup.where { springUser == u && springRoleGroup == rg }.deleteAll()
		}
	}

	static int removeAll(SpringUser u) {
		u == null ? 0 : SpringUserSpringRoleGroup.where { springUser == u }.deleteAll()
	}

	static int removeAll(SpringRoleGroup rg) {
		rg == null ? 0 : SpringUserSpringRoleGroup.where { springRoleGroup == rg }.deleteAll()
	}

	static constraints = {
		springUser validator: { SpringUser u, SpringUserSpringRoleGroup ug ->
			if (ug.springRoleGroup?.id) {
				SpringUserSpringRoleGroup.withNewSession {
					if (SpringUserSpringRoleGroup.exists(u.id, ug.springRoleGroup.id)) {
						return ['userGroup.exists']
					}
				}
			}
		}
	}

	static mapping = {
		id composite: ['springRoleGroup', 'springUser']
		version false
	}
}
