package com.gerald.isslng.umaas.manager;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class BlogApiTest {
	private static BlogInfoLoader loader = new BlogInfoLoader();
	static{
		loader.setBlogUrl("http://localhost:4000");
		loader.setRestTemplate(new RestTemplate());
	}
	@Test
	public void test() {
		List list = loader.get("tutorials");
		System.out.println(Arrays.toString(list.toArray()));
	}

}
