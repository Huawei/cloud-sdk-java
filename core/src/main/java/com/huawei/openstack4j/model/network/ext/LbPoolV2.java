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
package com.huawei.openstack4j.model.network.ext;

import java.util.List;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.network.ext.builder.LbPoolV2Builder;
import com.huawei.openstack4j.openstack.networking.domain.ext.ListItem;

/**
 * A v2 loadbanlance pool
 * @author emjburns
 */
public interface LbPoolV2 extends ModelEntity, Buildable<LbPoolV2Builder> {
    /**
     * @return id. The unique ID for the pool.
     */
    String getId();

    /**
     * @return tenantId.
     * Owner of the pool. Only an administrative user can specify a tenant ID other than its own.
     */
    String getTenantId();

    /**
     * @return Pool name. Does not have to be unique.
     */
    String getName();

    /**
     * @return Description for the pool.
     */
    String getDescription();

    /**
     * @return The protocol of the pool, which is TCP, HTTP, or UDP.
     */
    Protocol getProtocol();

    /**
     * @return The load-balancer algorithm, which is round-robin, least-connections, and so on. This value, which must be supported, is dependent on the load-balancer provider. Round-robin must be supported.
     */
    LbMethod getLbMethod();

    /**
     *
     * @return SessionPersistence
     */
    SessionPersistence getSessionPersistence();

    /**
     * @return The administrative state of the lb pool, which is up (true) or
     *         down (false).
     */
    boolean isAdminStateUp();

    /**
     * Listeners associated with the pool
     * @return listeners associated with the pool
     */
    List<ListItem> getListeners();


    /**
     * @return List of members that belong to the pool.
     */
    List<ListItem> getMembers();

    /**
     * @return Health monitor associated with the pool.
     */
    String getHealthMonitorId();
}
