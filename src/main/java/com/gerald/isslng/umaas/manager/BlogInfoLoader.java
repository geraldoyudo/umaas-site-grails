package com.gerald.isslng.umaas.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("blogLoader")
public class BlogInfoLoader {
	@Autowired
	private RestTemplate restTemplate;
	@Value("${umaas.blog}")
	private String blogUrl;
	
	public List get(String name){
		String url = blogUrl + "/api/" + name + ".json";
		System.out.println("Getting url " + url);
		List ret = restTemplate.getForObject(url, List.class);
		if(ret != null){
			for(Object item: new ArrayList(ret)){
				if(item ==null){
					ret.remove(item);
				}
			}
		}
		return ret;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}
	
	
}
