package com.gerald.isslng.umaas.manager.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import grails.plugin.springsecurity.userdetails.GormUserDetailsService;


public class UmaasAuthenticationProvider extends GormUserDetailsService implements AuthenticationProvider{
	
	@Value("${umaas.core:http://localhost:8040/umaas-core}")
	private String umaasCoreUrl;
	@Value("${umaas.manager.access.id:0000}")
	private String accessCodeId;
	@Value("${umaas.manager.access.code:0000}")
	private String accessCode;
	@Value("${umaas.manager.domain.id:1111}")
	private String domainId;
	
	@Autowired
	private RestTemplate restTemplate;
	MultiValueMap headers = new MultiValueMap();	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(!(authentication instanceof UsernamePasswordAuthenticationToken))
			return null;
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		checkAuthentication(username, password);
	    UmaasUser user  = getUser(username);
  	    Authentication auth = new UsernamePasswordAuthenticationToken(user, password, getAuthorities(user.getRoles()));
		return auth;
	}


	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	
	public String getUmaasCoreUrl() {
		return umaasCoreUrl;
	}


	public void setUmaasCoreUrl(String umaasCoreUrl) {
		this.umaasCoreUrl = umaasCoreUrl;
	}


	public String getAccessCodeId() {
		return accessCodeId;
	}


	public void setAccessCodeId(String accessCodeId) {
		this.accessCodeId = accessCodeId;
	}


	public String getAccessCode() {
		return accessCode;
	}


	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}


	public String getDomainId() {
		return domainId;
	}


	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}


	public RestTemplate getRestTemplate() {
		return restTemplate;
	}


	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	
	private void checkAuthentication (String username,String password)throws AuthenticationException{
		Map<String,String> params = new HashMap<>();
		params.put("user", username);
		params.put("password", password);
		params.put("domain", domainId );
		System.out.println("username "  + username);
		System.out.println("password" + password);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(umaasCoreUrl +
				"/domain/auth/authenticate")
		       .queryParam("user", username)
		       .queryParam("password", password)
		       .queryParam("domain", domainId);

		HttpEntity<?> entity = getHttpEntity();

		AuthenticationResult result = restTemplate.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.GET, 
		        entity, 
		        AuthenticationResult.class).getBody();
		System.out.println(result.isAuthenticated());
		System.out.println(Arrays.toString(result.getRoles().toArray()));
		if(!result.isAuthenticated()){
			throw new BadCredentialsException(Arrays.toString(result.getMessages().toArray()));
		}
		
	}
	
	private UmaasUser getUser(String username){
	
		Map<String,String> params = new HashMap<>();
		params.put("username", username);
		params.put("domain", domainId );
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(umaasCoreUrl +
				"/domain/appUsers/search/findByUsernameAndDomain")
		       .queryParam("username", username)
		       .queryParam("domain", domainId);
		HttpEntity<?> entity = getHttpEntity();
		UmaasUser user = restTemplate.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.GET, 
		        entity, 
		        UmaasUser.class).getBody();
		System.out.println(user.getUsername());
		return user;
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
		return roles.stream()
				.map((r) ->{ return new SimpleGrantedAuthority("ROLE_" + r.toUpperCase());} )
				.collect(Collectors.toList());
	}
	public <T> HttpEntity<T> getHttpEntity(T request){
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString(
				String.format("%s:%s", accessCodeId, accessCode).getBytes()));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<T>(request, headers);
	}
	public HttpEntity<?> getHttpEntity(){
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString(
				String.format("%s:%s", accessCodeId, accessCode).getBytes()));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity(headers);
	}
	public static class AuthenticationResult {
		@JsonProperty("auth")
		private boolean isAuthenticated;
		@JsonProperty(value = "roles", required = false)
		private Set<String> roles = new HashSet<>();
		@JsonProperty(value = "messages", required = false)
		private Set<String> messages = new HashSet<>();
		
		public AuthenticationResult() {
			// TODO Auto-generated constructor stub
		}
		
		public boolean isAuthenticated() {
			return isAuthenticated;
		}
		public void setAuthenticated(boolean isAuthenticated) {
			this.isAuthenticated = isAuthenticated;
		}
		public Set<String> getRoles() {
			return roles;
		}
		public void setRoles(Set<String> roles) {
			this.roles = roles;
		}
		
		public Set<String> getMessages() {
			return messages;
		}
		
		public void setMessages(Set<String> messages) {
			this.messages = messages;
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
		
		return getUser(username);
	}
}
