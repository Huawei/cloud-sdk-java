/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.util.io.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.io.ByteStreams;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.huawei.openstack4j.api.OSClient.OSClientV2;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.core.transport.internal.HttpExecutor;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneAccess;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneToken;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * Base Test class which handles Mocking a Webserver to fullfill and test
 * against JSON response objects from an OpenStack deployment
 *
 * @author Jeremy Unruh
 */
public abstract class AbstractTest {

	protected enum Service {
		//@off
        IDENTITY(5000),
        NETWORK(9696),
        COMPUTE(8774),
        BLOCK_STORAGE(8776),
        METERING(8087),
        TELEMETRY(8087),
        SHARE(8786),
        OBJECT_STORAGE(8800),
        BARBICAN(9311),
        MAGNUM(9511),
        ORCHESTRATION(8004),
        DATABASE(8779),
        TACKER(9890),
        IMAGE(9292),
        ARTIFACT(9494),
        CLUSTERING(8778),
        APP_CATALOG(8082),
        DNS(9001),
        WORKFLOW(8989),
        // huawei
        AUTO_SCALING(10000),
        CLOUD_EYE(10001),
        LOAD_BALANCER(10002),
        VOLUME_BACKUP(10003),
        SAHARA(10004),
        KEY_MANAGEMENT(10005),
        CLOUD_TRACE(10006),
        ANTI_DDOS(10007),
        MAAS(10008),
        NOTIFICATION(10010),
        MESSAGE_QUEUE(10011),
        ECS1_1(10012),
        EVS2_1(10013),
        VPC2(10014),
        VPC(10015),
        TAG_MANAGEMENT(10016),
        NAT(10017),
        BMS(10018),
        DEH(10019),
        IMS(10020),
        IMSV2(10021),
        ECS(10022),
        CSBS(10023),
        CDN(10024),
        FGS(10025),
		DSS(10026),
		EVS(10027);
        //@on

		private final int port;

		private Service(int port) {
			this.port = port;
		}

	}

	private final Logger LOG = LoggerFactory.getLogger(getClass().getName());

	protected static final String JSON_ACCESS = "/identity/v2/access.json";
	protected static final String JSON_TOKEN = "/identity/v3/authv3_project.json";
	protected static final String TOKEN_ID = "123456789";

	protected OSClientV2 osv2;
	protected OSClientV3 osv3;
	private String host;
	protected MockWebServer server = new MockWebServer();

	/**
	 * @return the service the API is using
	 */
	protected abstract Service service();

	@BeforeClass
	protected void startServer() throws UnknownHostException {

		InetAddress inetAddress = InetAddress.getByName("localhost");
		LOG.info("localhost inet address: " + inetAddress.toString());
		LOG.info("Tests using connector: " + HttpExecutor.create().getExecutorName() + " on " + getHost());

		try {
			LOG.info("Starting server on port " + service().port);
			server.start(service().port);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * responds with success status code and body from an Object
	 */
	protected void respondWith(Object body) throws IOException {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String bodyAsString = ObjectMapperSingleton.getContext(body.getClass()).writeValueAsString(body);
		respondWith(headers, 200, bodyAsString);
	}

	/**
	 * Responds with success status code and body from json resource file
	 *
	 * @param resource the json resource file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void respondWith(String resource) throws IOException {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		respondWith(headers, 200, getResource(resource));
	}

	/**
	 * Responds with specified status code and no body
	 *
	 * @param statusCode the status code to respond with
	 */
	protected void respondWith(int statusCode) {
		respondWith(null, statusCode, "");
	}

	/**
	 * Responds with specified status code, no body and optional headers
	 *
	 * @param headers    optional headers
	 * @param statusCode the status code to respond with
	 */
	protected void respondWith(Map<String, String> headers, int statusCode) {
		respondWith(headers, statusCode, "");
	}

	/**
	 * Responds with specified status code and json body
	 *
	 * @param statusCode the status code to respond with
	 * @param jsonBody   the json body
	 */
	protected void respondWith(int statusCode, String jsonBody) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		respondWith(headers, statusCode, jsonBody);
	}

	/**
	 * Responds with specified status code, body and optional headers
	 *
	 * @param headers    optional headers
	 * @param statusCode the status code to respond with
	 * @param body       the response body
	 */
	protected void respondWith(Map<String, String> headers, int statusCode, String body) {
		MockResponse r = new MockResponse();
		if (headers != null) {
			for (String name : headers.keySet()) {
				r.addHeader(name, headers.get(name));
			}
		}
		r.setBody(body);
		r.setResponseCode(statusCode);
		server.enqueue(r);
	}

	/**
	 * Responds with given header, status code, body from json resource file.
	 *
	 * @param headers    the specified header
	 * @param statusCode the status code to respond with
	 * @param resource   the json resource file
	 * @throws IOException Signals that an I/O exception has occurred
	 */
	protected void respondWithHeaderAndResource(Map<String, String> headers, int statusCode, String resource)
			throws IOException {
		InputStream is = getClass().getResourceAsStream(resource);
		respondWith(headers, statusCode, new String(ByteStreams.toByteArray(is)));
	}

	protected void respondWithCodeAndResource(int statusCode, String resource) throws IOException {
		InputStream is = getClass().getResourceAsStream(resource);
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		respondWith(headers, statusCode, new String(ByteStreams.toByteArray(is)));
	}

	/**
	 * Awaits, removes and returns the next request made to the mock server.
	 * Callers should use this to verify the request was sent as intended.
	 * This method will block until the request is available, possibly forever.
	 * <br/>
	 * <b>Be aware that this method will catch all the previous requests made
	 * to the mock server, also from other previous tests!
	 * Make sure to take all the requests made by methods in the same test class.</b>
	 *
	 * @return the head of the request queue
	 */
	protected RecordedRequest takeRequest() throws InterruptedException {
		return server.takeRequest();
	}

	protected String authURL(String path) {
		return String.format("http://%s:5000%s", getHost(), path);
	}

	@AfterClass(alwaysRun = true)
	protected void afterTest() {
		try {
			server.shutdown();
			LOG.info("Stopped server on port " + service().port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void associateClientV2(OSClientV2 osv2) {
		this.osv2 = osv2;
	}

	protected void associateClientV3(OSClientV3 osv3) {
		this.osv3 = osv3;
	}

	protected OSClientV2 osv2() {
		if (osv2 == null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
			mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

			try {
				String json = new String(Streams.readAll(getClass().getResourceAsStream(JSON_ACCESS)));
				LOG.info(getClass().getName());
				// LOG.info(getClass().getName() + ", JSON Access = " + json);
				json = json.replaceAll("127.0.0.1", getHost());
				// LOG.info("JSON Access = " + json);
				KeystoneAccess a = mapper.readValue(json, KeystoneAccess.class);
				a.applyContext(authURL("/v2.0"),
						new com.huawei.openstack4j.openstack.identity.v2.domain.Credentials("test", "test"));
				osv2 = OSFactory.clientFromAccess(a);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return osv2;
	}

	protected OSClientV3 osv3() {
		if (osv3 == null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
			mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

			try {
				String json = new String(Streams.readAll(getClass().getResourceAsStream(JSON_TOKEN)));
				LOG.info(getClass().getName());
				json = json.replaceAll("devstack.openstack.stack", getHost());
				KeystoneToken token = mapper.readValue(json, KeystoneToken.class);
				token.setId(TOKEN_ID);
				token.applyContext(authURL("/v3"),
						new com.huawei.openstack4j.openstack.identity.v3.domain.Credentials("admin", "test"));

				osv3 = OSFactory.clientFromToken(token);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return osv3;
	}

	protected String getResource(String resource) throws IOException {
		InputStream is = getClass().getResourceAsStream(resource);
		return new String(ByteStreams.toByteArray(is));
	}

	private String getHost() {
		/*
		 * try { if (host == null) host =
		 * InetAddress.getLocalHost().getHostAddress(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		if (host == null)
			return "127.0.0.1";

		return host;
	}
}
