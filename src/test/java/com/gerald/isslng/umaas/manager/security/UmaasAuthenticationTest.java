package com.gerald.isslng.umaas.manager.security;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;

public class UmaasAuthenticationTest {
	UmaasAuthenticationProvider provider = new UmaasAuthenticationProvider();
	
	@Before
	public void init(){
		provider.setAccessCode("0000");
		provider.setAccessCodeId("0000");
		provider.setDomainId("1111");
		provider.setUmaasCoreUrl("http://localhost:8040/umaas-core");
		provider.setRestTemplate(new RestTemplate());
	}
	@Test
	public void test() {
		Authentication auth = new UsernamePasswordAuthenticationToken("my-name", "aaaa");
		Authentication newAuth = provider.authenticate(auth);
		assertNotNull(newAuth);
		assertNotNull(newAuth.getPrincipal());
		System.out.println(newAuth.getPrincipal());
		UmaasUser user = (UmaasUser) newAuth.getPrincipal();
		System.out.println(user.getEmail());
		System.out.println(user.getId());
		System.out.println(user.getUsername());
	}

}
