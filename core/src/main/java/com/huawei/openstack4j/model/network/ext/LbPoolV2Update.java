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

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.network.ext.builder.LbPoolV2UpdateBuilder;

/**
 * An entity for updating a lbaas v2 lb pool
 * @author emjburns
 */
public interface LbPoolV2Update extends ModelEntity, Buildable<LbPoolV2UpdateBuilder> {

    /**
     *
     * @return Pool name. Does not have to be unique.
     */
    public String getName();

    /**
     *
     * @return Description for the pool.
     */
    public String getDescription();
    

    /**
     *
     * @return The administrative state of the lb pool, which is up (true) or
     *         down (false).
     */
    public Boolean isAdminStateUp();

    /**
     *
     * @return The load-balancer algorithm, which is round-robin, least-connections, and so on. This value, which must be supported, is dependent on the load-balancer provider. Round-robin must be supported.
     */
    public LbMethod getLbMethod();

    /**
     * Optional
     * @see Vip#getSessionPersistence()
     */
    public SessionPersistence getSessionPersistence();

}
