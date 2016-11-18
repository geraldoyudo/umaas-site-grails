package com.gerald.isslng.umaas.manager.security;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.gerald.isslng.umaas.manager.GeneralConfiguration;

public class UmaasAuthenticationTest {
	UmaasAuthenticationProvider provider = new UmaasAuthenticationProvider();
	
	@Before
	public void init(){
		provider.setAccessCode("0000");
		provider.setAccessCodeId("0000");
		provider.setDomainId("1111");
		provider.setUmaasCoreUrl("http://localhost:8040/umaas-core");
		GeneralConfiguration config = new  GeneralConfiguration();
		provider.setRestTemplate(config.restTemplate());
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
