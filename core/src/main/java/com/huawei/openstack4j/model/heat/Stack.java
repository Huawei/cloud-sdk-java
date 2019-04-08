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
package com.huawei.openstack4j.model.heat;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.GenericLink;

/**
 * This interface describes the getter-methods (and thus components) of a stack.
 * All getters map to the possible return values of
 * <code> GET /v1/{tenant_id}/stacks/{stack_name}/{stack_id}</code>
 * 
 * @see http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author Matthias Reisser
 * 
 */
public interface Stack extends ModelEntity {

	/**
	 * Returns the id of the stack
	 * 
	 * @return the id of the stack
	 */
	String getId();

	/**
	 * Returns the name of the stack
	 * 
	 * @return the name of the stack
	 */
	String getName();

	/**
	 * Returns the status of the stack
	 * 
	 * @return the status of the stack
	 */
	String getStatus();

	/**
	 * Returns the reason for the stack status
	 * 
	 * @return the stack status reason
	 */
	String getStackStatusReason();

	/**
	 * Returns the description of the stack
	 * 
	 * @return the description of the stack
	 */
	String getDescription();

	/**
	 * Returns the template description
	 * 
	 * @return the template description
	 */
	String getTemplateDescription();

	/**
	 * Returns the stacks timeout in minutes
	 * 
	 * @return the timeout in minutes
	 */
	Long getTimeoutMins();

	/**
	 * Returns the list of outputs of the stack
	 * 
	 * @return a List of Maps. Each Map consists of one element with: Key is the
	 *         Name of the output, Value is Json formatted containing
	 *         output_value, description and output_key
	 */
	List<Map<String, Object>> getOutputs();

	/**
	 * Returns the parameters of the stack
	 * @return the parameters of the stack. Key is the name, value is the value of the key
	 */
	Map<String, String> getParameters();

	/**
	 * Returns the timestamp of the creation.
	 * @return Timestamp formated like this: 2014-06-03T20:59:46Z
	 */
	String getCreationTime();

	/**
	 * Returns a list of links to resources of the stack
	 * @return a list of {@link GenericLink} objects
	 */
	List<GenericLink> getLinks();

	/**
	 * Returns the timestamp of the last update.
	 * @return Timestamp formated like this: 2014-06-03T20:59:46Z
	 */
	String getUpdatedTime();

	/**
	 * Returns the tags associated with the stack.
	 * @return The list of tags, separated by a comma.
	 */
	List<String> getTags();
}
