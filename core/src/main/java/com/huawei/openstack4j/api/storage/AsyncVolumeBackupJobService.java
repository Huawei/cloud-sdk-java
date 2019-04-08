/*******************************************************************************
 *  Copyright 2018 Huawei TLD                                   
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
package com.huawei.openstack4j.api.storage;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupJob;

/**
 * 
 * Works with {@link AsyncVolumeBackupService}, 
 * to provide a feature to get job detail for asynchronous volume backup jobs
 * 
 * @author QianBiao.NG
 * @date   2017-06-07 10:36:10
 */
public interface AsyncVolumeBackupJobService extends RestService {

	
	/**
	 * get asynchronous job details
	 * 
	 * @param jobId id of the job to fetch
	 * @return Asynchronous Volume Backup Job detail
	 */
	public AsyncVolumeBackupJob get(String jobId);

}
