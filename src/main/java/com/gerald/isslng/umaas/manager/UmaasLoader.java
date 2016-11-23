package com.gerald.isslng.umaas.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component("umaasLoader")
public class UmaasLoader {
	@Value("${umaas.core:http://localhost:8040/umaas-core}")
	private String umaasCoreUrl;
	@Value("${umaas.access.id:0000}")
	private String accessCodeId;
	@Value("${umaas.access.code:0000}")
	private String accessCode;
	@Value("${umaas.domain.id:1111}")
	private String domainId;
	@Autowired
	private RestTemplate restTemplate;

	public Map<String,Object> loadDomain(String id){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(umaasCoreUrl +
				"/domain/domains/")
				.path(id);
		return load(builder.build().toUriString());
	}

	public Map<String,Object> loadAccessCode(String id){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(umaasCoreUrl +
				"/domain/domainAccessCodes/")
				.path(id);
		Map<String,Object> code =  load(builder.build().toUriString());
		List<String> domains = getDomainList(id);
		code.put("domains", domains);
		return code;
	}

	public Map<String,Object> saveAccessCode(String id, final Map<String,Object> code){
		Map<String,Object> accessCode = new HashMap<>(code);
		List<String> domains = (List<String>) accessCode.remove("domains");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(umaasCoreUrl +
				"/domain/domainAccessCodes/")
				.path(id);
		setDomainList(id, domains);
		return save(builder.build().toUriString(), accessCode);
	}


	public Map<String,Object> saveDomain(String id, Map<String,Object> domain){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(umaasCoreUrl +
				"/domain/domains/")
				.path(id);
		return save(builder.build().toUriString(), domain);
	}

	public Map<String,Object> createDomain(Map<String,Object> domain){
		return create(umaasCoreUrl + "/domain/domains", domain);
	}

	public Map<String,Object> createAccessCode(Map<String,Object> code){
		return create(umaasCoreUrl + "/domain/domainAccessCodes", code);
	}

	private Map<String,Object> load(String url){

		HttpEntity<?> entity = getHttpEntity();
		Map<String,Object> ret = restTemplate.exchange(
		        url,
		        HttpMethod.GET,
		        entity,
		        Map.class).getBody();
		return ret;
	}

	private Map<String,Object> save(String url, Map<String,Object> object){
		HttpEntity<?> entity = getHttpEntity(object);
		Map<String,Object> ret = restTemplate.exchange(
		        url,
		        HttpMethod.PATCH,
		        entity,
		        Map.class).getBody();
		return ret;
	}
	private Map<String,Object> create(String url, Map<String,Object> object){
		HttpEntity<?> entity = getHttpEntity(object);
		Map<String,Object> ret = restTemplate.exchange(
		        url,
		        HttpMethod.POST,
		        entity,
		        Map.class).getBody();
		return ret;
	}
	private <T> HttpEntity<T> getHttpEntity(T request){
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString(
				String.format("%s:%s", accessCodeId, accessCode).getBytes()));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<T>(request, headers);
	}

	private HttpEntity<?> getHttpEntity(){
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString(
				String.format("%s:%s", accessCodeId, accessCode).getBytes()));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity(headers);
	}

	private List<String> getDomainList(String accessCodeId){
		//System.out.println("Getting mappings");
		/*
		 * steps
		 * 1. get mapping with entityType= ALL, entityId = "DOMAIN and priviledge = UPDATE
		 * get domains property in the meta
		 * return it
		 */
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(umaasCoreUrl +
				"/domain/domainAccessCodeMappings/search/findByAccessCodeIdAndEntityTypeAndEntityIdAndPriviledge")
				.queryParam("codeId", accessCodeId)
				.queryParam("entityType", "ALL")
				.queryParam("entityId", "domain")
				.queryParam("priviledge", "UPDATE");
		List<String> domains = new ArrayList<>();
		try{
			String url = builder.build().toUriString();
			//System.out.println(url);
			Map<String,Object>  mapping =  load(url);
			//System.out.println("Domain mapping");
			//System.out.println(mapping);
			List<String> domainList  = (List<String>) ((Map<String,Object>)
					mapping.get("meta")).get("domains");
			if(domainList != null)
				domains.addAll(domainList);
		}catch(NullPointerException ex){
			// do nothing
			//ex.printStackTrace();
		}catch (HttpClientErrorException ex){
		//	ex.printStackTrace();
			if(!ex.getStatusCode().equals(HttpStatus.NOT_FOUND))
				throw ex;

		}
		return domains;
	}
	private void setDomainList(String accessCodeId, List<String> domainList){
		/*
		 * steps
		 * 1. get mapping with entityType= ALL, entityId = "DOMAIN and priviledge = UPDATE
		 * get domains property in the meta
		 * return it
		 */
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(umaasCoreUrl +
				"/domain/domainAccessCodeMappings/search/findByAccessCodeIdAndEntityTypeAndEntityIdAndPriviledge")
				.queryParam("codeId", accessCodeId)
				.queryParam("entityType", "ALL")
				.queryParam("entityId", "domain")
				.queryParam("priviledge", "UPDATE");

		String id = null;
		String url = builder.build().toUriString();
		System.out.println(url);
		try{
			Map<String,Object>  mapping =  load(url);
			String[] segments = ((Map<String,Object>)((Map<String,Object>)
					mapping.get("_links"))
					.get("self")).get("href").toString().split("/");
			//System.out.println(segments);
			id = segments[segments.length -1];
			//System.out.println(id);
		}catch (HttpClientErrorException ex){
			if(!ex.getStatusCode().equals(HttpStatus.NOT_FOUND))
				throw ex;
			Map<String,Object> mapping = new HashMap<>();
			mapping.put("entityId", "domain");
			mapping.put("entityType", "ALL");
			mapping.put("accessCode", "/domain/domainAccessCodes/" + accessCodeId);
			mapping.put("priviledge", "UPDATE");
			Map<String,Object> meta = new HashMap<>();
			meta.put("domains", domainList);
			mapping.put("meta", meta);
			System.out.println(mapping);
			String createUrl =  umaasCoreUrl + "/domain/domainAccessCodeMappings";
			create(createUrl, mapping);
		}

		if(id != null)
			saveDomainMapping(id, domainList);
	}

	private void saveDomainMapping(String domainMappingId, List<String> domainList){
		List<String> list = new ArrayList<>();
		if(domainList != null){
			list.addAll(domainList);
		}
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(umaasCoreUrl +
				"/domain/domainAccessCodeMappings/")
				.path(domainMappingId);
		Map<String, Object> meta = null;
		String url = builder.build().toUriString();
		try{
			Map<String,Object> mapping = load(url);
			meta = (Map<String,Object>)mapping.get("meta");
		}catch(NullPointerException ex){
			// do nothing
		}
		if(meta == null){
			meta = new HashMap<String,Object>();
		}
		meta.put("domains", list);
		Map<String,Object> value = new HashMap<>();
		value.put("meta", meta);
		Map<String,Object> mapping = save(url, value);
		//System.out.println(mapping);
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

}
