grails.plugin.springsecurity.providerNames = [
   'umaasAuthenticationProvider',
   'anonymousAuthenticationProvider',
   'rememberMeAuthenticationProvider']
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap = [
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
   [pattern: '/login',          access: ['permitAll']],
   [pattern: '/login/**',       access: ['permitAll']],
   [pattern: '/logout',         access: ['permitAll']],
   [pattern: '/logout/**',      access: ['permitAll']],
   [pattern: '/home/**',      access: ['isFullyAuthenticated()']],
   [pattern: '/application',      access: ['permitAll']],
   [pattern: '/profile',      access: ['isAuthenticated()']],
   [pattern: '/**',      access: ['permitAll']]
]
