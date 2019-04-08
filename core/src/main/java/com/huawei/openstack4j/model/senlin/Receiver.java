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
package com.huawei.openstack4j.model.senlin;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.huawei.openstack4j.model.ResourceEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Receiver.
 * All getters map to the possible return values of
 * <code> GET /v1/receivers/​{receiver_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface Receiver extends ResourceEntity {

	/**
	 * Returns the action of the receiver
	 *
	 * @return the action of the receiver
	 */
	String getAction();

	/**
	 * Returns the cluster id of the receiver
	 *
	 * @return the cluster id of the receiver
	 */
	String getClusterID();

	/**
	 * Returns the created at time of the receiver
	 *
	 * @return the created at time of the receiver
	 */
	Date getCreatedAt();

	/**
	 * Returns the domain of the receiver
	 *
	 * @return the domain of the receiver
	 */
	String getDomain();

	/**
	 * Returns the project of the receiver
	 *
	 * @return the project of the receiver
	 */
	String getProject();

	/**
	 * Returns the type of the receiver
	 *
	 * @return the type of the receiver
	 */
	String getType();

	/**
	 * Returns the updated at time of the receiver
	 *
	 * @return the updated at time of the receiver
	 */
	Date getUpdatedAt();

	/**
	 * Returns the user of the receiver
	 *
	 * @return the user of the receiver
	 */
	String getUser();

	/**
	 * Returns the actor of the receiver
	 *
	 * @return the actor of the receiver
	 */
	Map<String, Object> getActor();

	/**
	 * Returns the channel of the receiver
	 *
	 * @return the channel of the receiver
	 */
	Map<String, Object> getChannel();

	/**
	 * Returns the params of the receiver
	 *
	 * @return the params of the receiver
	 */
	Map<String, Object> getParams();

	/**
	 * Returns the WebHook of the receiver
	 *
	 * @return the WebHook of the receiver
	 */
	String getWebHook();
}
