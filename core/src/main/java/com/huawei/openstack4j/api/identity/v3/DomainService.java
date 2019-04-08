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
package com.huawei.openstack4j.api.identity.v3;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.identity.v3.Domain;

/**
 * Identity V3 Domain Service
 *
 */
public interface DomainService extends RestService {

    /**
     * Creates a new domain
     *
     * @param domain the Domain to create
     * @return the new domain
     */
    Domain create(Domain domain);

    /**
     * Creates a new domain
     *
     * @param name the name of the new domain
     * @param description the description of the new domain
     * @param enabled the enabled status of the new domain
     * @return the new domain
     */
    Domain create(String name, String description, boolean enabled);

    /**
     * Updates an existing domain
     *
     * @param domain the domain set to update
     * @return the updated domain
     */
    Domain update(Domain domain);

    /**
     * Get detailed information on a domain by id
     *
     * @param domainId the domain identifier
     * @return the domain
     */
    Domain get(String domainId);

    /**
     * Get detailed information on a domain by name
     *
     * @param domainName the domain name
     * @return the domain
     */
    List<? extends Domain> getByName(String domainName);

    /**
     * Deletes a domain by id
     *
     * @param domainId the domain id
     * @return the ActionResponse
     */
    ActionResponse delete(String domainId);

    /**
     * lists all domains the current token has access to
     *
     * @return list of domains
     */
    List<? extends Domain> list();

}
