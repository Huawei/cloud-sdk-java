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

package com.huawei.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2Update;
import com.huawei.openstack4j.model.network.ext.builder.HealthMonitorV2UpdateBuilder;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorV2.httpMethodType;
import com.google.common.base.MoreObjects;

/**
 * Entity used to update lbaas v2 heathmonitor
 * @author emjburns
 */
@JsonRootName("healthmonitor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronHealthMonitorV2Update implements HealthMonitorV2Update {

    private static final long serialVersionUID = 1L;

    private Integer delay;

    private String name;
    private Integer timeout;

    /**
     * Number of allowed connection failures before changing the status of the member to INACTIVE. A valid value is from 1 to 10.
     */
    @JsonProperty("max_retries")
    private Integer maxRetries;

    /**
     * The HTTP method that the monitor uses for requests.
     */
    @JsonProperty("http_method")
    private httpMethodType httpMethod ;

    /**
     * URL
     */
    @JsonProperty("url_path")
    private String urlPath ;

    /**
     * default 200
     */
    @JsonProperty("expected_codes")
    private String expectedCodes  ;

    /**
     * The administrative state of the health monitor, which is up (true) or down (false)
     */
    @JsonProperty("admin_state_up")
    private Boolean adminStateUp;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getDelay(){
        return delay;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrlPath(){
        return urlPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getTimeout(){
        return timeout;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMaxRetries(){
        return maxRetries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public httpMethodType getHttpMethod(){
        return httpMethod;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExpectedCodes(){
        return expectedCodes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isAdminStateUp(){
        return adminStateUp;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("delay", delay)
                .add("urlPath", urlPath)
                .add("adminStateUp", adminStateUp)
                .add("expectedCodes", expectedCodes)
                .add("httpMethod", httpMethod)
                .add("maxRetries",maxRetries)
                .add("timeout", timeout)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorV2UpdateBuilder toBuilder(){
        return new HealthMonitorV2UpdateConcreteBuilder(this);
    }

    public static HealthMonitorV2UpdateBuilder builder(){
        return new HealthMonitorV2UpdateConcreteBuilder();
    }

    public static class HealthMonitorV2UpdateConcreteBuilder implements HealthMonitorV2UpdateBuilder {
        NeutronHealthMonitorV2Update m;

        public HealthMonitorV2UpdateConcreteBuilder(){
            this (new NeutronHealthMonitorV2Update());
        }

        public HealthMonitorV2UpdateConcreteBuilder(NeutronHealthMonitorV2Update m){
            this.m = m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2Update build(){
            return m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2UpdateBuilder from(HealthMonitorV2Update in){
            m = (NeutronHealthMonitorV2Update) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2UpdateBuilder delay(Integer delay){
            m.delay = delay;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2UpdateBuilder urlPath(String urlPath){
            m.urlPath = urlPath;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2UpdateBuilder expectedCodes(String expectedCodes){
            m.expectedCodes = expectedCodes;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2UpdateBuilder httpMethod(httpMethodType httpMethod){
            m.httpMethod = httpMethod;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2UpdateBuilder maxRetries(Integer maxRetries){
            m.maxRetries = maxRetries;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2UpdateBuilder adminStateUp(Boolean adminStateUp){
            m.adminStateUp = adminStateUp;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public HealthMonitorV2UpdateBuilder timeout(Integer timeout){
            m.timeout = timeout;
            return this;
        }

		@Override
		public HealthMonitorV2UpdateBuilder name(String name) {
			 m.name = name;
	         return this;
		}
    }
}
