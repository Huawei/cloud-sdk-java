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
package com.huawei.openstack4j.core.transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.huawei.openstack4j.api.EndpointTokenProvider;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.common.Payload;
import com.huawei.openstack4j.openstack.identity.signer.AKSK;
import com.huawei.openstack4j.openstack.identity.signer.AKSK.Credential;
import com.huawei.openstack4j.openstack.internal.OSClientSession;
import com.huawei.openstack4j.openstack.internal.OSClientSessionAKSK;

/**
 * A Request Delegate which aids in building the request that is compatible with the OpenStack Rest API. The request is used to encoding as well as keeping reference to 
 * the return type
 * 
 * @param <R> the entity return type
 * @author Jeremy Unruh
 */
public class HttpRequest<R> {

	String region;
	String endpoint;
	String path;
	Class<R> returnType;
	Object entity;
	String contentType = ClientConstants.CONTENT_TYPE_JSON;
	HttpMethod method = HttpMethod.GET;
	String json;
	private Config config;
	private Map<String, List<Object>> queryParams;
	private ServiceType service;
	private Map<String, Object> headers = new HashMap<String, Object>();
	private Function<String, String> endpointFunc;
	public HttpRequest() { }

	/**
	 * Creates a new HttpRequest
	 *
	 * @param endpoint the endpoint URI
	 * @param path the path which will be appended to the endpoint URI
	 * @param method the method the method type to invoke
	 * @param entity the entity (used for posts)
	 * @param returnType the expected return type
	 */
	public HttpRequest(String endpoint, String path, HttpMethod method, ModelEntity entity, Class<R> returnType) {
		this.endpoint = endpoint;
		this.path = path;
		this.method = method;
		this.entity = entity;
	}

	/**
	 * A build for creating HttpRequest objects
	 *
	 * @return the request builder
	 */
	public static RequestBuilder<Void> builder() {
		return new RequestBuilder<Void>(Void.class);
	}

	/**
	 * A build for creating HttpRequest objects
	 *
	 * @param <R> the expected return type
	 * @param returnType the return type
	 * @return the request builder
	 */
	public static <R> RequestBuilder<R> builder(Class<R> returnType) {
		return new RequestBuilder<R>(returnType);
	}

	/**
	 * @return the method this request will use
	 */
	public HttpMethod getMethod() {
		return method;
	}
	
	/**
	 * @return the content type for the request
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @return the endpoint URI
	 */
	public String getEndpoint() {
		if (endpointFunc != null)
			return endpointFunc.apply(endpoint);
		return endpoint;
	}
	
	/**
	 * @return the http path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * If JSON is explicitly set vs an entity then this method will return a JSON String otherwise Empty
	 * @return JSON String form or Empty
	 */
	public String getJson() {
		return (json == null) ? "" : json;
	}
	
	/**
	 * @return true, if a JSON Object has been set
	 */
	public boolean hasJson() {
		return (json != null);
	}
	
	/**
	 * @return the return type expected after invocation
	 */
	public Class<R> getReturnType() {
		return returnType;
	}
	
	/**
	 * @return the entity to post
	 */
	public Object getEntity() {
		return entity;
	}
	
	/**
	 * @return true, if query params have been added
	 */
	public boolean hasQueryParams() {
		return queryParams != null && !queryParams.isEmpty();
	}

	/**
	 * @return the request query params
	 */
	public Map<String, List<Object>> getQueryParams() {
		return queryParams;
	}

	/**
	 * @return the headers to apply
	 */
	public Map<String, Object> getHeaders() {
		return headers;
	}

	/**
	 * @return true, if headers have been added
	 */
	public boolean hasHeaders() {
		return !headers.isEmpty();
	}
	
	public RequestBuilder<R> toBuilder() {
	    return new RequestBuilder<R>(this);
	}
	
	/**
	 * @return the client configuration associated with this request
	 */
	public Config getConfig() {
	    return config != null ? config : Config.DEFAULT;
	}
	
	public ServiceType getService() {
		return service;
	}
	
	public String getRegion() {
		return region;
	}
	
	public static final class RequestBuilder<R> {

		HttpRequest<R> request;
		EndpointTokenProvider provider;
		ServiceType service;
		
		public RequestBuilder(HttpRequest<R> request) {
		    this.request = request;
		}
		
		public RequestBuilder(Class<R> returnType) {
			request = new HttpRequest<R>();
			request.returnType = returnType;
		}

		/**
		 * @see HttpRequest#getEndpoint()
		 */
		public RequestBuilder<R> endpoint(String endpoint) {
			request.endpoint = endpoint;
			return this;
		}

		/**
		 * @see HttpRequest#getPath()
		 */
		public RequestBuilder<R> path(String path) {
			request.path = path;
			return this;
		}

		/**
		 * @see HttpRequest#getMethod()
		 */
		public RequestBuilder<R> method(HttpMethod method) {
			request.method = method;
			return this;
		}
		
		/**
		 * A Function which allows manipulation of the endpoint depending on the service API utilizing it
		 * @param endpointFunc the function to modify the current endpoint into a resulting endpoint
		 * @return this
		 */
		public RequestBuilder<R> endpointFunction(Function<String, String> endpointFunc) {
			request.endpointFunc = endpointFunc;
			return this;
		}
 
		/**
		 * Flags the request method as PUT
		 *
		 * @return the request builder
		 */
		public RequestBuilder<R> methodPut() {
			request.method = HttpMethod.PUT;
			return this;
		}

		/**
		 * Flags the request method as GET
		 *
		 * @return the request builder
		 */
		public RequestBuilder<R> methodGet() {
			request.method = HttpMethod.GET;
			return this;
		}

		/**
		 * Flags the request method as DELETE
		 *
		 * @return the request builder
		 */
		public RequestBuilder<R> methodDelete() {
			request.method = HttpMethod.DELETE;
			return this;
		}

		/**
		 * Flags the request method as POST
		 *
		 * @return the request builder
		 */
		public RequestBuilder<R> methodPost() {
			request.method = HttpMethod.POST;
			return this;
		}
		
		/**
		 * @see HttpRequest#getEntity()
		 */
		public RequestBuilder<R> entity(Object entity) {
			request.entity = entity;
			return this;
		}
		
		/**
		 * @see HttpRequest#getEntity()
		 */
		public RequestBuilder<R> entity(Payload<?> entity) {
			if (entity != null)
			  request.entity = entity.open();
			return this;
		}

		/**
         * Sets a client configuration to use with this session
         */
        public RequestBuilder<R> config(Config config) {
            request.config = config;
            return this;
        }
		
		/**
		 * Pushes the Map of Headers into the existing headers for this request
		 * @param headers the headers to append
		 * @return the request builder
		 */
		public RequestBuilder<R> headers(Map<String, ? extends Object> headers) {
			request.getHeaders().putAll(headers);
			return this;
		}
		
		/**
		 * Adds a new Header to the request
		 * @param name the header name
		 * @param value the header value
		 * @return the request builder
		 */
		public RequestBuilder<R> header(String name, Object value) {
			request.getHeaders().put(name, value);
			return this;
		}
		
		/**
		 * The endpoint Service Type
		 *
		 * @param service the service type
		 * @return the request builder
		 */
		public RequestBuilder<R> serviceType(ServiceType service) {
			request.service = service;
			this.service = service;
			return this;
		}

		/**
		 * Adds a Key/Value based Query Param     
		 *
		 * @param key the key
		 * @param value the value
		 * @return the request builder
		 */
		public RequestBuilder<R> queryParam(String key, Object value) {
		    if (value == null)
		        return this;

			if(value.toString().contains("{") && value.toString().contains("}")){
				value = value.toString().replace("{","%7B").replace("}","%7D");
			}

			if (request.queryParams == null)
				request.queryParams = Maps.newHashMap();

			if (request.queryParams.containsKey(key)) {
				List<Object> values = request.queryParams.get(key);
				values.add(value);
			} else {
				List<Object> values = new ArrayList<Object>();
				values.add(value);
				request.queryParams.put(key, values);
			}
			return this;
		}
        /**
         * Updates a Key/Value based Query Param     
         *
         * @param key the key
         * @param value the value
         * @return the request builder
         */
        public RequestBuilder<R> updateQueryParam(String key, Object value) {
            if (value == null)
                return this;
            
            if (request.queryParams == null)
                request.queryParams = Maps.newHashMap();

            List<Object> values = new ArrayList<Object>();
            values.add(value);
            request.queryParams.put(key, values);
            
            return this;
        }
		/**
		 * A Provider which will return the current Authorization Token
		 *
		 * @param provider the provider
		 * @return the request builder
		 */
		public RequestBuilder<R> endpointTokenProvider(EndpointTokenProvider provider) {
			this.provider = provider;
			return this;
		}
		
		/**
		 * AdHoc JSON object to Post/Put
		 *
		 * @param json the JSON object in String form
		 * @return the request builder
		 */
		public RequestBuilder<R> json(String json) {
			request.json = json;
			return this;
		}
		
		/**
		 * Overrides the default content type for the request
		 * 
		 * @param contentType the content type to use in the request
		 * @return the request builder
		 */
		public RequestBuilder<R> contentType(String contentType) {
		    if (contentType != null)
		        request.contentType = contentType;
			return this;
		}

		/**
		 * Builds the HttpRequest
		 *
		 * @return HttpRequest
		 */
		public HttpRequest<R> build() {
			if (provider != null)
			{
				request.endpoint = provider.getEndpoint(service);
				if (provider.getTokenId() != null)
				    request.getHeaders().put(ClientConstants.HEADER_X_AUTH_TOKEN, provider.getTokenId());

				// (AK/SK) authentication
				OSClientSession<?, ?> session = OSClientSession.getCurrent();
				if (session instanceof OSClientSessionAKSK) {
					OSClientSessionAKSK aksk = ((OSClientSessionAKSK) session);
					request.region = aksk.getRegion();
					Credential credential = Credential.builder().ak(aksk.getAccessKey()).sk(aksk.getSecretKey()).build();
					HashMap<String, String> headers = AKSK.sign(request, credential);
					request.getHeaders().putAll(headers);
					String projectId = ((OSClientSessionAKSK) session).getProjectId();
					String domainId = ((OSClientSessionAKSK) session).getDomainId();
					//Determine if it is a global service
					if (projectId != null && !"".equals(projectId)){
						request.getHeaders().put(ClientConstants.HEADER_X_PROJECT_ID, aksk.getProjectId());
					}else if(domainId != null && !"".equals(domainId) ){
						request.getHeaders().put(ClientConstants.HEADER_X_DOMAIN_ID, aksk.getDomainId());
					}
				}
				
				//Add micro version
				Config config = request.getConfig();
				if(config != null && config.getMicroversions() != null && config.getMicroversions().size()>0){
					Map<String,String> microversions = new HashMap<String,String>();
					for (Map.Entry<String, Double> entry : config.getMicroversions().entrySet()) {
						if(!"compute".equalsIgnoreCase(entry.getKey())){
							throw new IllegalArgumentException("Micro version only supports compute");
						}
						if(service.getType().equalsIgnoreCase(entry.getKey())){
							String servicesStr = entry.getKey() + " " + entry.getValue();
							Double version  = entry.getValue();
							if(version > 2.26){
								microversions.put(ClientConstants.HEADER_OPENSTACK_API_VERSION, servicesStr);
							}else{
								microversions.put(ClientConstants.HEADER_X_OPENSTACK_NOVA_API_VERSION, version.toString());
							}
							this.headers(microversions);
						}
					}
				}
			}
			
			return request;
		}

	}
}
