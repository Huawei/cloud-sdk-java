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
/*
 * 
 */
package com.huawei.openstack4j.model.workflow;

import java.util.Date;
import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;


/**
 * Base interface for all execution objects.
 * 
 * @author Renat Akhmerov
 */
public interface Execution extends ModelEntity {
	/**
	 * @return The id of this execution.
	 */
	String getId();

    /**
     * @return The description of this execution.
     */
    String getDescription();

    /**
     * @return The name of the workflow that this task belongs to.
     */
    String getWorkflowName();

    /**
	 * @return Execution state.
	 */
	State getState();

	/**
	 * @return Execution state info.
	 */
	String getStateInfo();

    /**
     * @return The list of tags.
     */
    List<String> getTags();

    /**
	 * @return The time that this entity was createdAt at.
	 */
	Date getCreated();

	/**
	 * @return The time that this entity was last updatedAt at.
	 */
	Date getUpdated();
}
