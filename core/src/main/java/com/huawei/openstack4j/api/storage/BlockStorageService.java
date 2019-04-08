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
package com.huawei.openstack4j.api.storage;

import com.huawei.openstack4j.api.storage.ext.BlockStorageServiceService;
import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.storage.block.BlockLimits;

/**
 * Block Storage (Cinder) Service Operation API
 * 
 * @author Jeremy Unruh
 */
public interface BlockStorageService extends RestService {

	/**
	 * @return the Volume Service API
	 */
	BlockVolumeService volumes();
	
	/**
	 * @return the Volume Snapshot Service API
	 */
	BlockVolumeSnapshotService snapshots();
	
	CinderZoneService zones();
	
	/**
	 * Gets the Absolute limits used by this tenant
	 * 
	 * @return the absolute limits
	 */
	BlockLimits getLimits();
	
	/**
	 * The block storage quota-set service.  
	 * 
	 * @return the quota-set service
	 */
	BlockQuotaSetService quotaSets();

	/**
	 * The block storage get_pools service.
	 *
	 * @return the scheduler stats service
	 */
	SchedulerStatsGetPoolService schedulerStatsPools();
	
	/**
	 * @return the Volume Service API
	 */
	BlockVolumeBackupService backups();
	
	/**
	 * OTC new volume backup service
	 * @return the Asynchronous Volume Service API
	 */
	AsyncVolumeBackupService asyncBackups();
	
	/**
	 * OTC new asynchronous volume backup job service
	 * @return the Asynchronous Volume Service Job API
	 */
	AsyncVolumeBackupJobService jobs();
	
	/**
	 * OTC new volume backup policy
	 * 
	 * @return the volume backup policy API
	 */
	BlockVolumeBackupPolicyService policies();
	
	/**
	 * The block storage service service
	 * 
	 * @return ServiceService
	 */
	 BlockStorageServiceService services();
}
