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
package com.huawei.openstack4j.api.tacker;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.tacker.Vnfd;

/**
 * <p>Tacker - OpenStack NFV Orchestration</p>
 * 
 * <p>Tacker is an official OpenStack project building a Generic VNF Manager (VNFM) and a NFV Orchestrator (NFVO) to deploy and operate 
 * Network Services and Virtual Network Functions (VNFs) on an NFV infrastructure platform like OpenStack. 
 * It is based on ETSI MANO Architectural Framework and provides a functional stack to Orchestrate Network Services end-to-end using VNFs.</p>
 * 
 * <p>VNFD Stands for Virtual Network Functions Descriptor. This is the VNF Catalog Management (TOSCA-YAML Template) API Service..</p>
 * 
 * <p>
	 * <u>NFV Catalog</u>:
	 * <ul>
	 * 		<li>VNF Descriptors.</li>
	 * 		<li>Network Services Decriptors.</li>
	 * 		<li>VNF Forwarding Graph Descriptors.</li>
	 * </ul>
 * </p>
 * 
 * @see <a href="http://docs.openstack.org/developer/tacker/devref/mano_api.html">Official Tacker Documentation</a>
 * @see <a href="https://github.com/openstack/tacker/blob/master/doc/source/devref/mano_api.rst">Official GitHub Tacker Reference</a>
 * 
 * @author Vishvesh Deshmukh
 * @date Aug 11, 2016
 */
public interface VnfdService extends RestService {
	
	/**
     * List vnfs - Lists instantiated vnfs in VNF Manager
     *
     * @return list of all Vnfd(s)
     */
    List<? extends Vnfd> list();
    
    /**
     * List vnfs - Lists instantiated vnfs in VNF Manager filtered by parameters.
     * 
     * @param filteringParams map (name, value) of filtering parameters
     * @return filtered list of Vnfd(s)
     */
    List<? extends Vnfd> list(Map<String, String> filteringParams);
    
    /**
     * Show vnfd - Show information for a specified vnfdId.
     *
     * @param vnfdId the Vnfd identifier
     * @return the Vnfd or null if not found
     */
    Vnfd get(String vnfdId);
    
    /**
     * Delete vnfd - Deletes a specified vnfdId from the VNF catalog.
     * @param vnfdId the Vnfd identifier
     * @return the action response
     */
    ActionResponse delete(String vnfdId);
    
    /**
     * Create vnfd - Create a vnfd entry based on the vnfd template.
     * @param vnfd 
     * @return Vnfd
     */
    Vnfd create(Vnfd vnfd);

}
