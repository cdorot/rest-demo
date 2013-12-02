package com.digiplug.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class UserResourceIT {

	@Test
	public void getUser() throws URISyntaxException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
				.setPath("/jersey-service-demo/webapi/users/1").build();

		HttpGet httpGet = new HttpGet(uri);

		CloseableHttpResponse response = httpClient.execute(httpGet);

		try {
			int statusCode = response.getStatusLine().getStatusCode();

			assertEquals(200, statusCode);

			String content = IOUtils.toString(response.getEntity().getContent(), CharEncoding.UTF_8);

			assertEquals("{\"firstname\":\"John\",\"id\":1,\"lastname\":\"Doe\"}", content);
		} finally {
			response.close();
		}
	}

	@Test
	public void getUserAsJsonP() throws URISyntaxException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
				.setPath("/jersey-service-demo/webapi/users/jsonp/1").addParameter("jsonp", "mycallback").build();

		HttpGet httpGet = new HttpGet(uri);

		httpGet.setHeader("Accept", "application/javascript");

		CloseableHttpResponse response = httpClient.execute(httpGet);

		try {
			int statusCode = response.getStatusLine().getStatusCode();

			assertEquals(200, statusCode);

			String content = IOUtils.toString(response.getEntity().getContent(), CharEncoding.UTF_8);

			assertEquals("mycallback({\"firstname\":\"John\",\"id\":1,\"lastname\":\"Doe\"})", content);
		} finally {
			response.close();
		}
	}

	@Test
	public void postUser() throws URISyntaxException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
				.setPath("/jersey-service-demo/webapi/users").build();

		HttpEntity entity = new StringEntity("{ \"firstname\":\"Jean\",\"lastname\":\"DUPONT\" }",
				ContentType.APPLICATION_JSON);
		HttpPost httpPost = new HttpPost(uri);

		httpPost.setEntity(entity);

		CloseableHttpResponse response = httpClient.execute(httpPost);

		try {
			int statusCode = response.getStatusLine().getStatusCode();

			assertEquals(201, statusCode);

			String content = IOUtils.toString(response.getEntity().getContent(), CharEncoding.UTF_8);

			assertTrue(content.matches("\\{\"firstname\":\"Jean\",\"id\":\\d+,\"lastname\":\"DUPONT\"\\}"));
		} finally {
			response.close();
		}
	}

	@Test
	public void postInvalidUser() throws URISyntaxException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
				.setPath("/jersey-service-demo/webapi/users").build();

		HttpEntity entity = new StringEntity("{ \"lastname\":\"DUPONT\" }", ContentType.APPLICATION_JSON);
		HttpPost httpPost = new HttpPost(uri);

		httpPost.setEntity(entity);

		CloseableHttpResponse response = httpClient.execute(httpPost);

		try {
			int statusCode = response.getStatusLine().getStatusCode();

			assertEquals(400, statusCode);
		} finally {
			response.close();
		}
	}

}
