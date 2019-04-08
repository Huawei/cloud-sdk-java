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
/******************************************************************************* 	                                                                                 
 *  Huawei has modified this source file.
 * 	Copyright 2018 Huawei Technologies Co.,Ltd.                                         
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
 * *******************************************************************************/
package com.huawei.openstack4j.connectors.jersey2;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;

import com.huawei.openstack4j.api.exceptions.ConnectionException;
import com.huawei.openstack4j.api.exceptions.ResponseException;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.core.transport.HttpExecutorService;
import com.huawei.openstack4j.core.transport.HttpRequest;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.openstack.internal.OSAuthenticator;
import com.huawei.openstack4j.openstack.internal.OSClientSession;

/**
 * HttpExecutor is the default implementation for HttpExecutorService which is responsible for interfacing with Jersey and mapping common status codes, requests and responses
 * back to the common API
 * 
 * @author Jeremy Unruh
 */
public class HttpExecutorServiceImpl implements HttpExecutorService {

    private static final String NAME = "Jersey 2 Connector";

    
    /**
     * {@inheritDoc}
     */
    @Override
    public <R> HttpResponse execute(HttpRequest<R> request) {
        try {
            return invoke(request);
        }
        catch (ResponseException re) {
            throw re;
        }
        catch (Exception e) {
            throw new ConnectionException("Error during execution: " + e, 0, e);
        }
    }

    /**
     * Invokes the given request
     *
     * @param <R> the return type
     * @param request the request to invoke
     * @return the response
     * @throws Exception the exception
     */
    private <R> HttpResponse invoke(HttpRequest<R> request) throws Exception {

        HttpCommand<R> command = HttpCommand.create(request);

        try {
            return invokeRequest(command);
        } catch (ProcessingException pe) {
            throw new ConnectionException(pe.getMessage(), 0, pe);
        } catch (ClientErrorException e) {
            throw ResponseException.mapException(e.getResponse().getStatusInfo().toString(), e.getResponse().getStatus(), e);
        }
    }

//    private <R> HttpResponse invokeRequest(HttpCommand<R> command) throws Exception {
//        Response response = command.execute();
//        if(!command.getRequest().getHeaders().containsKey(ClientConstants.HEADER_OS4J_AUTH)){
////        	response.getStatusInfo().getReasonPhrase();
//        	if(response.getStatus() == 401){
//        		if (command.getRetries() < 5){
//                    OSAuthenticator.reAuthenticate();
//                    if(OSClientSession.getCurrent()==null) return HttpResponseImpl.wrap(response);
//                    command.getRequest().getHeaders().put(ClientConstants.HEADER_X_AUTH_TOKEN, OSClientSession.getCurrent().getTokenId());
//                    return invokeRequest(command.incrementRetriesAndReturn());
//                } else {
//                	command.ReplyRetries();
//                } 
//        	}
//        	else {
//            	command.ReplyRetries();
//            }
//        }
//        return HttpResponseImpl.wrap(response);
//    }

    private <R> HttpResponse invokeRequest(HttpCommand<R> command) throws Exception {
        Response response = command.execute();
        if (command.getRetries() == 0 && response.getStatus() == 401 && !command.getRequest().getHeaders().containsKey(ClientConstants.HEADER_OS4J_AUTH))
        {
            OSAuthenticator.reAuthenticate();
            command.getRequest().getHeaders().put(ClientConstants.HEADER_X_AUTH_TOKEN, OSClientSession.getCurrent().getTokenId());
            return invokeRequest(command.incrementRetriesAndReturn());
        }
        return HttpResponseImpl.wrap(response);
    }
    
    @Override
    public String getExecutorDisplayName() {
        return NAME;
    }

}
