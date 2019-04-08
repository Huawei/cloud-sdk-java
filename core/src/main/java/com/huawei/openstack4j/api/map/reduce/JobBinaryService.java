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
package com.huawei.openstack4j.api.map.reduce;

import java.io.InputStream;
import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Payload;
import com.huawei.openstack4j.model.map.reduce.JobBinary;
import com.huawei.openstack4j.model.map.reduce.options.JobBinaryListOptions;

/**
 * The manipulation of {@link JobBinary}
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface JobBinaryService extends RestService {

	/**
	 * List job binaries by filter options
	 * 
	 * @return list of job binaries or empty
	 */
	List<? extends JobBinary> list(JobBinaryListOptions options);

	/**
	 * Get a job binary by ID
	 * 
	 * @param JobBinaryId the job binary identifier
	 * @return the job binary or null if not found
	 */
	JobBinary get(String JobBinaryId);

	/**
	 * Create a new job binary
	 *
	 * @param jobBinary the job binary to create
	 * @return the created job binary
	 */
	JobBinary create(JobBinary jobBinary);

	/**
	 * Update an exists job binary
	 *
	 * @param jobBinary the job binary to update
	 * @return the updated job binary
	 */
	JobBinary update(JobBinary jobBinary);

	/**
	 * Delete the specified job binary
	 * 
	 * @param JobBinaryId the job binary identifier
	 * @return the action response
	 */
	ActionResponse delete(String JobBinaryId);

	/**
	 * Retrieves data of specified job binary object
	 * 
	 * @param JobBinaryId the job binary identifier
	 * @return Job Binary data
	 */
	Payload<InputStream> getData(String JobBinaryId);
}
