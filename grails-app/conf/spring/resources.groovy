import com.gerald.isslng.umaas.manager.security.UmaasAuthenticationProvider
// Place your Spring DSL code here
beans = {
  userDetailsService(UmaasAuthenticationProvider)
}
