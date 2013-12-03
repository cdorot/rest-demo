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
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserResourceIT {

	static final String USER_RESOURCE_PATH = "/cxf-service/webapi/users";

	private CloseableHttpClient httpClient;

	private CloseableHttpResponse response;

	private URIBuilder userResourceURIBuilder;

	public UserResourceIT() {
		super();

		this.httpClient = HttpClients.createDefault();
		this.userResourceURIBuilder = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080);
	}

	@Before
	public void setup() {
		this.response = null;
	}

	@After
	public void teardown() throws IOException {
		this.response.close();
	}

	@Test
	public void getUser() throws URISyntaxException, IOException {
		URI uri = this.userResourceURIBuilder.setPath(USER_RESOURCE_PATH + "/1").build();

		HttpGet httpGet = new HttpGet(uri);

		this.response = this.httpClient.execute(httpGet);

		int statusCode = response.getStatusLine().getStatusCode();
		String content = IOUtils.toString(response.getEntity().getContent(), CharEncoding.UTF_8);

		assertEquals(200, statusCode);
		assertEquals("{\"id\":1,\"firstname\":\"John\",\"lastname\":\"Doe\"}", content);
	}

	@Test
	public void getUserAsJsonP() throws URISyntaxException, IOException {
		URI uri = this.userResourceURIBuilder.setPath(USER_RESOURCE_PATH + "/1").addParameter("jsonp", "mycallback")
				.build();

		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader("Accept", "application/javascript");

		this.response = this.httpClient.execute(httpGet);

		int statusCode = response.getStatusLine().getStatusCode();
		String content = IOUtils.toString(response.getEntity().getContent(), CharEncoding.UTF_8);

		assertEquals(200, statusCode);
		assertEquals("mycallback({\"id\":1,\"firstname\":\"John\",\"lastname\":\"Doe\"});", content);
	}

	@Test
	public void postUser() throws URISyntaxException, IOException {
		URI uri = this.userResourceURIBuilder.setPath(USER_RESOURCE_PATH).build();

		HttpEntity entity = new StringEntity("{ \"firstname\":\"Jean\",\"lastname\":\"DUPONT\" }",
				ContentType.APPLICATION_JSON);
		HttpPost httpPost = new HttpPost(uri);

		httpPost.setEntity(entity);

		this.response = this.httpClient.execute(httpPost);

		int statusCode = response.getStatusLine().getStatusCode();
		String content = IOUtils.toString(response.getEntity().getContent(), CharEncoding.UTF_8);

		assertEquals(201, statusCode);
		assertTrue(content.matches("\\{\"id\":\\d+,\"firstname\":\"Jean\",\"lastname\":\"DUPONT\"\\}"));
	}

	@Test
	public void postInvalidUser() throws URISyntaxException, IOException {
		URI uri = this.userResourceURIBuilder.setPath(USER_RESOURCE_PATH).build();

		HttpEntity entity = new StringEntity("{ \"lastname\":\"DUPONT\" }", ContentType.APPLICATION_JSON);
		HttpPost httpPost = new HttpPost(uri);

		httpPost.setEntity(entity);

		this.response = this.httpClient.execute(httpPost);

		int statusCode = response.getStatusLine().getStatusCode();

		assertEquals(500, statusCode);
	}

	@Test
	public void putUser() throws URISyntaxException, IOException {
		URI uri = this.userResourceURIBuilder.setPath(USER_RESOURCE_PATH + "/2").build();

		HttpEntity entity = new StringEntity("{ \"id\":2,\"firstname\":\"Lisa\",\"lastname\":\"Simpson\" }",
				ContentType.APPLICATION_JSON);
		HttpPut httpPut = new HttpPut(uri);

		httpPut.setEntity(entity);

		this.response = this.httpClient.execute(httpPut);

		int statusCode = response.getStatusLine().getStatusCode();
		String content = IOUtils.toString(response.getEntity().getContent(), CharEncoding.UTF_8);

		assertEquals(200, statusCode);
		assertEquals("{\"id\":2,\"firstname\":\"Lisa\",\"lastname\":\"Simpson\"}", content);
	}

	@Test
	public void deleteUser() throws URISyntaxException, IOException {
		URI uri = this.userResourceURIBuilder.setPath(USER_RESOURCE_PATH + "/3").build();

		HttpDelete httpDelete = new HttpDelete(uri);

		this.response = this.httpClient.execute(httpDelete);

		int statusCode = response.getStatusLine().getStatusCode();

		assertEquals(204, statusCode);
	}

}
