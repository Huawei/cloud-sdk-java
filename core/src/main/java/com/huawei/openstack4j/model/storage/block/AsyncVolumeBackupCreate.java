/*******************************************************************************
 *  Copyright 2017 Huawei TLD                                        
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
package com.huawei.openstack4j.model.storage.block;

import com.huawei.openstack4j.model.ModelEntity;

/**
 *
 * @author Woo Cubic
 * @date   2017-06-07 08:37:14
 */
public interface AsyncVolumeBackupCreate extends ModelEntity {

	/**
	 * @return the name of the Volume Transfer.
	 */
	String getName();

	/**
	 * @return the description of the volume backup
	 */
	String getDescription();

	/**
	 * @return The UUID of the volume.
	 */
	String getVolumeId();
	
	/**
	 * @return The UUID of the volume snapshot.
	 */
	String getSnapshotId();

}
