package org.spring.auth

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class SpringRoleGroupSpringRole implements Serializable {

	private static final long serialVersionUID = 1

	SpringRoleGroup springRoleGroup
	SpringRole springRole

	@Override
	boolean equals(other) {
		if (other instanceof SpringRoleGroupSpringRole) {
			other.springRoleId == springRole?.id && other.springRoleGroupId == springRoleGroup?.id
		}
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (springRoleGroup) builder.append(springRoleGroup.id)
		if (springRole) builder.append(springRole.id)
		builder.toHashCode()
	}

	static SpringRoleGroupSpringRole get(long springRoleGroupId, long springRoleId) {
		criteriaFor(springRoleGroupId, springRoleId).get()
	}

	static boolean exists(long springRoleGroupId, long springRoleId) {
		criteriaFor(springRoleGroupId, springRoleId).count()
	}

	private static DetachedCriteria criteriaFor(long springRoleGroupId, long springRoleId) {
		SpringRoleGroupSpringRole.where {
			springRoleGroup == SpringRoleGroup.load(springRoleGroupId) &&
			springRole == SpringRole.load(springRoleId)
		}
	}

	static SpringRoleGroupSpringRole create(SpringRoleGroup springRoleGroup, SpringRole springRole) {
		def instance = new SpringRoleGroupSpringRole(springRoleGroup: springRoleGroup, springRole: springRole)
		instance.save()
		instance
	}

	static boolean remove(SpringRoleGroup rg, SpringRole r) {
		if (rg != null && r != null) {
			SpringRoleGroupSpringRole.where { springRoleGroup == rg && springRole == r }.deleteAll()
		}
	}

	static int removeAll(SpringRole r) {
		r == null ? 0 : SpringRoleGroupSpringRole.where { springRole == r }.deleteAll()
	}

	static int removeAll(SpringRoleGroup rg) {
		rg == null ? 0 : SpringRoleGroupSpringRole.where { springRoleGroup == rg }.deleteAll()
	}

	static constraints = {
		springRole validator: { SpringRole r, SpringRoleGroupSpringRole rg ->
			if (rg.springRoleGroup?.id) {
				SpringRoleGroupSpringRole.withNewSession {
					if (SpringRoleGroupSpringRole.exists(rg.springRoleGroup.id, r.id)) {
						return ['roleGroup.exists']
					}
				}
			}
		}
	}

	static mapping = {
		id composite: ['springRoleGroup', 'springRole']
		version false
	}
}
