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
package com.huawei.openstack4j.api.storage;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.VolumeSnapshot;

/**
 * OpenStack (Cinder) Volume Snapshot Operations API.
 *
 * @author Jeremy Unruh
 */
public interface BlockVolumeSnapshotService extends RestService {

	/**
	 * Lists detailed information for all Block Storage snapshots that the tenant who submits the request can access.
	 *
	 * @return List of VolumeSnapshot
	 */
	List<? extends VolumeSnapshot> list();

	/**
	 * Returns list of Block Storage snapshots filtered by parameters.
	 * 
	 * @param filteringParams map (name, value) of filtering parameters
	 * @return 
	 */
	List<? extends VolumeSnapshot> list(Map<String, String> filteringParams);

	/**
	 * Shows information for a specified snapshot.
	 *
	 * @param snapshotId the snapshot identifier
	 * @return the volume snapshot or null
	 */
	VolumeSnapshot get(String snapshotId);
	
	/**
	 * Deletes a specified snapshot
	 *
	 * @param snapshotId the snapshot identifier
	 * @return the action response
	 */
	ActionResponse delete(String snapshotId);
	
	/**
	 * Updates the Name and/or Description for the specified snapshot
	 *
	 * @param snapshotId the snapshot identifier
	 * @param name the new name
	 * @param description the new description
	 * @return the action response
	 */
	ActionResponse update(String snapshotId, String name, String description);
	
	/**
	 * Creates a snapshot, which is a point-in-time copy of a volume. You can create a volume from the snapshot.
	 * 
	 * NOTE: the volume ID within the snapshot must be set or an NullPointerException will be thrown
	 *
	 * @param snapshot the snapshot to create
	 * @return the newly created snapshot
	 */
	VolumeSnapshot create(VolumeSnapshot snapshot);

//	/**
//	 * Updating an EVS Snapshot
//	 *
//	 * @param snapshotId
//	 * @param snapshot
//	 * @return
//	 */
//	SnapshotDetail update(String snapshotId, SnapshotUpdate snapshot);
//
//	/**
//	 * Querying Details About EVS Snapshots
//	 *
//	 * @return
//	 */
//	List<? extends SnapshotDetail> listDetail();
//
//	/**
//	 * Querying Details About EVS Snapshots
//	 *
//	 * @param options
//	 * @return
//	 */
//	List<? extends SnapshotDetail> listDetail(SnapshotListOptions options);
//
//	/**
//	 * Adding Metadata of an EVS Snapshot
//	 *
//	 * @param snapshotId
//	 * @param metadata
//	 * @return
//	 */
//	SnapshotMetadata createMetadata(String snapshotId, SnapshotMetadata metadata);
//
//	/**
//	 * Querying Metadata of an EVS Snapshot
//	 *
//	 * @param snapshotId
//	 * @return
//	 */
//	SnapshotMetadata getMetadata(String snapshotId);
//
//	/**
//	 * Updating Metadata of an EVS Snapshot
//	 *
//	 * @param snapshotId
//	 * @param metadata
//	 * @return
//	 */
//	SnapshotMetadata updateMetadata(String snapshotId, SnapshotMetadata metadata);
//
//	/**
//	 * Deleting One Piece of EVS Snapshot Metadata
//	 *
//	 * @param snapshotId
//	 * @param key
//	 * @return
//	 */
//	ActionResponse deleteMeta(String snapshotId, String key);
//
//	/**
//	 * Querying One Piece of EVS Snapshot Metadata
//	 *
//	 * @param snapshotId
//	 * @param key
//	 * @return
//	 */
//	SnapshotMeta getMeta(String snapshotId, String key);
//
//	/**
//	 * Updating One Piece of EVS Snapshot Metadata
//	 *
//	 * @param snapshotId
//	 * @param key
//	 * @param metadata
//	 * @return
//	 */
//	SnapshotMeta updateMeta(String snapshotId, String key, SnapshotMeta metadata);
}
