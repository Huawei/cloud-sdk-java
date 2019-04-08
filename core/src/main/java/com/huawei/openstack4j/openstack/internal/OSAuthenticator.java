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
package com.huawei.openstack4j.openstack.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.OSClient.OSClientV2;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.api.client.CloudProvider;
import com.huawei.openstack4j.api.exceptions.AuthenticationException;
import com.huawei.openstack4j.api.exceptions.OS4JException;
import com.huawei.openstack4j.api.types.Facing;
import com.huawei.openstack4j.core.transport.*;
import com.huawei.openstack4j.core.transport.internal.HttpExecutor;
import com.huawei.openstack4j.model.identity.AuthStore;
import com.huawei.openstack4j.model.identity.AuthVersion;
import com.huawei.openstack4j.model.identity.v3.Authentication;
import com.huawei.openstack4j.model.identity.v3.Token;
import com.huawei.openstack4j.openstack.common.Auth;
import com.huawei.openstack4j.openstack.common.Auth.Type;
import com.huawei.openstack4j.openstack.identity.v2.domain.Credentials;
import com.huawei.openstack4j.openstack.identity.v2.domain.KeystoneAccess;
import com.huawei.openstack4j.openstack.identity.v2.domain.RaxApiKeyCredentials;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneAuth;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneToken;
import com.huawei.openstack4j.openstack.identity.v3.domain.TokenAuth;
import com.huawei.openstack4j.openstack.internal.OSClientSession.OSClientSessionV2;

import static com.huawei.openstack4j.core.transport.HttpExceptionHandler.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for authenticating and re-authenticating sessions for V2 and V3
 * of the Identity API
 */
public class OSAuthenticator {

    private static final String TOKEN_INDICATOR = "Tokens";
    private static final Logger LOG = LoggerFactory.getLogger(OSAuthenticator.class);

    /**
     * Invokes authentication to obtain a valid V3 Token, throws an
     * UnsupportedOperationException for an V2 attempt.
     *
     * @param auth the authentication credentials
     * @param endpoint the identity endpoint
     * @param perspective the network facing perspective
     * @param config the client configuration
     * @return the OSClient
     */
    @SuppressWarnings("rawtypes")
    public static OSClient invoke(AuthStore auth, String endpoint, Facing perspective, Config config,
            CloudProvider provider) {
        SessionInfo info = new SessionInfo(endpoint, perspective, false, provider);
        if (auth.getVersion() == AuthVersion.V2) {
            return authenticateV2((com.huawei.openstack4j.openstack.identity.v2.domain.Auth) auth, info, config);
        }
        return authenticateV3((KeystoneAuth) auth, info, config);
    }

    /**
     * Invokes V3 authentication via an existing token
     * 
     * @param auth the token authentication
     * @param endpoint the identity endpoint
     * @param perspective the network facing perspective
     * @param config the client configuration
     * @return the OSClient
     */
    @SuppressWarnings("rawtypes")
    public static OSClient invoke(KeystoneAuth auth, String endpoint, Facing perspective, Config config,
            CloudProvider provider) {
        SessionInfo info = new SessionInfo(endpoint, perspective, false, provider);
        return authenticateV3(auth, info, config);
    }

    /**
     * Invokes V2 authentication via an existing token
     * 
     * @param auth the token authentication
     * @param endpoint the identity endpoint
     * @param perspective the network facing perspective
     * @param config the client configuration
     * @return the OSClient
     */
    @SuppressWarnings("rawtypes")
    public static OSClient invoke(com.huawei.openstack4j.openstack.identity.v2.domain.TokenAuth auth, String endpoint,
            Facing perspective, Config config, CloudProvider provider) {
        SessionInfo info = new SessionInfo(endpoint, perspective, false, provider);
        return authenticateV2(auth, info, config);
    }

    /**
     * Re-authenticates/renews the token for the current Session
     */
    @SuppressWarnings("rawtypes")
    public static void reAuthenticate() {

        LOG.debug("Re-Authenticating session due to expired Token or invalid response");

        OSClientSession session = OSClientSession.getCurrent();

        switch (session.getAuthVersion()) {
        case V2:
            KeystoneAccess access = ((OSClientSessionV2) session).getAccess().unwrap();
            SessionInfo info = new SessionInfo(access.getEndpoint(), session.getPerspective(), true,
                    session.getProvider());
            Auth auth = (Auth) ((access.isCredentialType()) ? access.getCredentials() : access.getTokenAuth());
            authenticateV2((com.huawei.openstack4j.openstack.identity.v2.domain.Auth) auth, info, session.getConfig());
            break;
        case AKSK: throw new AuthenticationException("Authorization information is wrong", 401);
        case V3:
        default:
            Token token = ((OSClientSessionV3) session).getToken();
            info = new SessionInfo(token.getEndpoint(), session.getPerspective(), true, session.getProvider());
            authenticateV3((KeystoneAuth) token.getCredentials(), info, session.getConfig());
            break;
        }
    }

    private static OSClientV2 authenticateV2(com.huawei.openstack4j.openstack.identity.v2.domain.Auth auth, SessionInfo info,
            Config config) {
        HttpRequest<KeystoneAccess> request = HttpRequest.builder(KeystoneAccess.class)
                .header(ClientConstants.HEADER_OS4J_AUTH, TOKEN_INDICATOR).endpoint(info.endpoint)
                .method(HttpMethod.POST).path("/tokens").config(config).entity(auth).build();

        HttpResponse response = HttpExecutor.create().execute(request);
        if (response.getStatus() >= 400) {
            try {
                throw mapException(response.getStatusMessage(), response.getStatus());
            } finally {
                HttpEntityHandler.closeQuietly(response);
            }
        }

        KeystoneAccess access = response.getEntity(KeystoneAccess.class);

        if (auth.getType() == Type.CREDENTIALS) {
            access = access.applyContext(info.endpoint, (Credentials) auth);
        } else if (auth.getType() == Type.RAX_APIKEY) {
            access = access.applyContext(info.endpoint, (RaxApiKeyCredentials) auth);
        } else {
            access = access.applyContext(info.endpoint, (com.huawei.openstack4j.openstack.identity.v2.domain.TokenAuth) auth);
        }

        if (!info.reLinkToExistingSession)
            return OSClientSession.OSClientSessionV2.createSession(access, info.perspective, info.provider, config);

        OSClientSession.OSClientSessionV2 current = (OSClientSessionV2) OSClientSession.getCurrent();
        current.access = access;
        return current;
    }

    private static OSClientV3 authenticateV3(KeystoneAuth auth, SessionInfo info, Config config) {
        if (auth.getType().equals(Type.TOKENLESS)){
            Map<String, String> headers = new HashMap<String, String>();
            Authentication.Scope.Project project = auth.getScope().getProject();
            if (project != null){
                if (!isEmpty(project.getId()))
                    headers.put(ClientConstants.HEADER_X_PROJECT_ID, project.getId());
                if (!isEmpty(project.getName()))
                    headers.put(ClientConstants.HEADER_X_PROJECT_NAME, project.getName());
                Authentication.Scope.Domain domain = project.getDomain();
                if (domain != null){
                    if (!isEmpty(domain.getId()))
                        headers.put(ClientConstants.HEADER_X_PROJECT_DOMAIN_ID, domain.getId());
                    if (!isEmpty(domain.getName()))
                        headers.put(ClientConstants.HEADER_X_PROJECT_DOMAIN_NAME, domain.getName());
                }
            }else{
                Authentication.Scope.Domain domain = auth.getScope().getDomain();
                if (domain != null){
                    if (!isEmpty(domain.getId()))
                        headers.put(ClientConstants.HEADER_X_DOMAIN_ID, domain.getId());
                    if (!isEmpty(domain.getName()))
                        headers.put(ClientConstants.HEADER_X_DOMAIN_NAME, domain.getName());
                }
            }
            KeystoneToken keystoneToken = new KeystoneToken();
            keystoneToken.setEndpoint(info.endpoint);
            return OSClientSessionV3.createSession(keystoneToken, null, null, config).headers(headers);
        }

        HttpRequest<KeystoneToken> request = HttpRequest.builder(KeystoneToken.class)
                .header(ClientConstants.HEADER_OS4J_AUTH, TOKEN_INDICATOR).endpoint(info.endpoint)
                .method(HttpMethod.POST).path("/auth/tokens").config(config).entity(auth).build();

        HttpResponse response = HttpExecutor.create().execute(request);

        if (response.getStatus() >= 400) {
            try {
                throw mapException(response.getStatusMessage(), response.getStatus());
            } finally {
                HttpEntityHandler.closeQuietly(response);
            }
        }
        KeystoneToken token = response.getEntity(KeystoneToken.class);
        token.setId(response.header(ClientConstants.HEADER_X_SUBJECT_TOKEN));

        if (auth.getType() == Type.CREDENTIALS) {
            token = token.applyContext(info.endpoint, auth);
        } else {
            if (token.getProject() != null) {
                token = token.applyContext(info.endpoint, new TokenAuth(token.getId(),
                        auth.getScope().getProject().getName(), auth.getScope().getProject().getId()));
            } else {
                token = token.applyContext(info.endpoint, new TokenAuth(token.getId(),
                        auth.getScope().getDomain().getName(), auth.getScope().getDomain().getId()));
            }
        }

        String reqId = response.header(ClientConstants.X_OPENSTACK_REQUEST_ID);

        if (!info.reLinkToExistingSession) {
        	OSClientSessionV3 v3 = OSClientSessionV3.createSession(token, info.perspective, info.provider, config);
        	v3.reqId = reqId;
            return v3;
        }

        OSClientSessionV3 current = (OSClientSessionV3) OSClientSessionV3.getCurrent();
        current.token = token;
       
        current.reqId = reqId;
        return current;
    }

    private static class SessionInfo {
        String endpoint;
        Facing perspective;
        boolean reLinkToExistingSession;
        CloudProvider provider;

        SessionInfo(String endpoint, Facing perspective, boolean reLinkToExistingSession, CloudProvider provider) {
            this.endpoint = endpoint;
            this.perspective = perspective;
            this.reLinkToExistingSession = reLinkToExistingSession;
            this.provider = provider;
        }
    }

    private static boolean isEmpty(String str){
        if (str != null && str.length() > 0)
            return false;
        return true;
    }
}
