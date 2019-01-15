

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.spring.auth.SpringUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.spring.auth.SpringUserSpringRole'
grails.plugin.springsecurity.authority.className = 'org.spring.auth.SpringRole'
grails.plugin.springsecurity.authority.groupAuthorityNameField = 'authorities'
grails.plugin.springsecurity.useRoleGroups = true
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	//Rule example [pattern: '/importADUser/**', access: ['permitAll']],
	[pattern: '/importADUser/**', access: ['permitAll']],
	[pattern: '/bioasterUserRegistrationRequest/**', access: ['permitAll']],
	[pattern: '/bioasterAdminDashBoard/**', access: ['permitAll']],
	[pattern: '/bioasterUserRegistrationValidation/**', access: ['permitAll']],
	[pattern: '/bioasterOrganizationManager/**', access: ['permitAll']],
	[pattern: '/bioasterUserManager/**', access: ['permitAll']],
	[pattern: '/bioasterGroupManager/**', access: ['permitAll']],
	[pattern: '/loginUserSpace/**', access: ['permitAll']],
	[pattern: '/endUserAccountManager/**', access: ['permitAll']],
	[pattern: '/accessLog/**', access: ['permitAll']],
	[pattern: '/accessControlException/**', access: ['permitAll']],
	[pattern: '/bioasterSecurityPolicyTester/**', access: ['permitAll']],
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]


grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'org.spring.auth.PersistentLogin'

//Added by pengfei
grails.plugin.springsecurity.password.algorithm='bcrypt'
grails.plugin.springsecurity.rememberMe.cookieName='grails_remember_me'
grails.plugin.springsecurity.rememberMe.alwaysRemember=false
grails.plugin.springsecurity.rememberMe.tokenValiditySeconds=31*24*60*60
grails.plugin.springsecurity.rememberMe.parameter='_spring_security_remember_me'
grails.plugin.springsecurity.rememberMe.key='monitoringApp'
grails.plugin.springsecurity.rememberMe.useSecureCookie=true
grails.plugin.springsecurity.rememberMe.persistent=true
grails.plugin.databasemigration.updateOnStart = true
grails.plugin.springsecurity.logout.postOnly=false
