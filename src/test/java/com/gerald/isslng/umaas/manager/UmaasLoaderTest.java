package com.gerald.isslng.umaas.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class UmaasLoaderTest {
	UmaasLoader loader = new UmaasLoader();
	@Before
	public void init(){
		loader.setAccessCode("0000");
		loader.setAccessCodeId("0000");
		loader.setDomainId("1111");
		loader.setUmaasCoreUrl("http://localhost:8040/umaas-core");
		GeneralConfiguration config = new  GeneralConfiguration();
		loader.setRestTemplate(config.restTemplate());
		loader.setFileLimitService("com.gerald.umaas.file_limit.service");
	}
	
	@Test
	public void testCreateDomain(){
		Map<String,Object> domain = new HashMap<>();
		domain.put("code", "code" + System.currentTimeMillis());
		domain.put("name", "name" + System.currentTimeMillis());
		domain = loader.createDomain(domain);
		assertNotNull(domain);
		System.out.println(domain);
	}
	
	@Test
	public void testCreateAccessCode(){
		Map<String,Object> code = new HashMap<>();
		code.put("code", "code" + System.currentTimeMillis());
		code = loader.createAccessCode(code);
		assertNotNull(code);
		System.out.println(code);
	}
	@Test
	public void testLoadDomain() {
		Map<String,Object> domain = loader.loadDomain("1111");
		assertNotNull(domain);
		System.out.println(domain);
	}
	
	@Test
	public void testLoadLimit() {
		Map<String,Object> limit = loader.loadLimit("1111");
		assertNotNull(limit);
		System.out.println(limit);
	}
	
	@Test
	public void testSaveDomain() {
		Map<String,Object> value = new HashMap<>();
		Map<String,Object> domain = loader.loadDomain("1111");
		String code = domain.get("code").toString();
		value.put("code", "test");
		domain = loader.saveDomain("1111", value);
		assertNotNull(domain);
		assertEquals("test", domain.get("code").toString());
		value.put("code", code);
		loader.saveDomain("1111", value);
		System.out.println(domain);
	}

	
	@Test
	public void testLoadAccessCode() {
		Map<String,Object> ac = loader.loadAccessCode("57f77af85e61ed1bc4f34fa5");
		assertNotNull(ac);
		System.out.println(ac);
	}
	
	@Test
	public void testSaveAccessCode() {
		Map<String,Object> value = new HashMap<>();
		Map<String,Object> ac = loader.loadAccessCode("57f77af85e61ed1bc4f34fa5");
		value.put("domains", Arrays.asList("domain1-1", "domain2-1", "domain3-1"));
		ac = loader.saveAccessCode("57f77af85e61ed1bc4f34fa5", value);
		assertNotNull(ac);
	}

}
