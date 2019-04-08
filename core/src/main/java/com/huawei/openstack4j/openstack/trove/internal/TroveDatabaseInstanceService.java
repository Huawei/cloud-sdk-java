 /*******************************************************************************
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
 *******************************************************************************/
package com.huawei.openstack4j.openstack.trove.internal;

import java.util.HashMap;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.database.domain.Volume;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseInstance;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseInstanceCreate;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseReplicaInstanceCreate;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseInstance.DatabaseInstanceWrap;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseInstance.DatabaseInstanceWraps;

/**
 * The implementation of manipulation of {@link DatabaseInstance}
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:13:41
 */
public class TroveDatabaseInstanceService extends BaseTroveServices {

	/**
	 * list all database instances
	 * 
	 * @return a list of {@link DatabaseInstance} instances
	 */
	public List<DatabaseInstance> list() {
		List<DatabaseInstance> result = Lists.newArrayList();
		List<DatabaseInstanceWrap> list = get(DatabaseInstanceWraps.class, uri("/instances")).execute().getList();
		for (DatabaseInstanceWrap wrap : list) {
			result.add(wrap.getInstance());
		}
		return result;
	}

	/**
	 * get the detail of database instance
	 * 
	 * @param instanceId	database instance identifier
	 * @return {@link DatabaseInstance} instance
	 */
	public DatabaseInstance get(String instanceId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		DatabaseInstance instance = get(DatabaseInstance.class, uri("/instances/%s", instanceId)).execute();
		return instance;
	}

	/**
	 * create a new database instance 
	 * 
	 * @param creation	a model represent the attributes of database instance creation
	 * @return {@link DatabaseInstance} instance
	 */
	public DatabaseInstance create(DatabaseInstanceCreate creation) {
		Preconditions.checkNotNull(creation, "parameter `creation` should not be null");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(creation.getName()),
				"parameter `creation.name` should not be empty");
		Preconditions.checkNotNull(creation.getDatastore(), "parameter `creation.datastore` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(creation.getDatastore().getVersion()),
				"parameter `creation.datastore.version` should not be empty");
		Preconditions.checkNotNull(creation.getDatastore().getType(),
				"parameter `creation.datastore.type` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(creation.getFlavorRef()),
				"parameter `creation.flavorRef` should not be empty");
		Preconditions.checkArgument(creation.getUsers() != null && creation.getUsers().size() > 0,
				"parameter `creation.users` should not be empty");
		Preconditions.checkArgument(creation.getVolume() != null, "parameter `creation.volume` should not be null");
		Preconditions.checkArgument(creation.getVolume().getSize() != null, "parameter `creation.volume.size` should not be null");
		return post(DatabaseInstance.class, uri("/instances")).entity(creation).execute();
	}

	/**
	 * create replicas of a database instance 
	 * 
	 * @param creation	a model represent the attributes of database replica instance creation
	 * @return {@link DatabaseInstance} instance
	 */
	public DatabaseInstance createReplica(DatabaseReplicaInstanceCreate creation) {
		Preconditions.checkNotNull(creation, "parameter `creation` should not be null");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(creation.getName()),
				"parameter `creation.name` should not be empty");
		Preconditions.checkNotNull(creation.getDatastore(), "parameter `creation.datastore` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(creation.getDatastore().getVersion()),
				"parameter `creation.datastore.version` should not be empty");
		Preconditions.checkNotNull(creation.getDatastore().getType(),
				"parameter `creation.datastore.type` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(creation.getFlavorRef()),
				"parameter `creation.flavorRef` should not be empty");
		Preconditions.checkArgument(creation.getVolume() != null, "parameter `creation.volume` should not be null");
		Preconditions.checkArgument(creation.getVolume().getSize() != null, "parameter `creation.volume.size` should not be null");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(creation.getReplicaOf()),
				"parameter `creation.replicaOf` should not be empty");
		return post(DatabaseInstance.class, uri("/instances")).entity(creation).execute();
	}

	/**
	 * delete a database instance
	 * @param instanceId	database instance identifier
	 * @return				asynchronous job id of the database deletion job
	 */
	public ActionResponse delete(String instanceId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		return deleteWithResponse(uri("/instances/%s", instanceId)).execute();
	}

	/**
	 * restart database instance asynchronously
	 * 
	 * @param instanceId 	database instance identifier
	 * @return	{@link ActionResponse} instance
	 */
	public ActionResponse restart(String instanceId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		HashMap<Object, Object> entity = Maps.newHashMap();
		entity.put("restart", Maps.newHashMap());
		return postWithResponse(uri("/instances/%s/action", instanceId)).entity(entity).execute();
	}

	/**
	 * Resize the flavor of database instance asynchronously
	 * 
	 * @param instanceId 	database instance identifier
	 * @param volumeSize 	the size of the volume
	 * @return	{@link ActionResponse} instance
	 */
	public ActionResponse resize(String instanceId, int volumeSize) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		Preconditions.checkArgument(volumeSize >= 110 && volumeSize <= 2000,
				"parameter `volumeSize` shoule between 110 and 2000");

		ResizeVolumeRequest entity = new ResizeVolumeRequest();
		entity.setVolume(Volume.builder().size(volumeSize).build());

		return postWithResponse(uri("/instances/%s/action", instanceId)).entity(entity).execute();
	}

	/**
	 * Resize the flavor of database instance asynchronously
	 * 
	 * @param instanceId 	database instance identifier
	 * @param flavorRef 	the instance flavor identifier
	 * @return	{@link ActionResponse} instance
	 */
	public ActionResponse resize(String instanceId, String flavorRef) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(flavorRef), "parameter `flavorRef` should not be empty");

		ResizeFlavorRequest entity = new ResizeFlavorRequest();
		entity.setFlavorRef(flavorRef);
		return postWithResponse(uri("/instances/%s/action", instanceId)).entity(entity).execute();
	}

	@JsonRootName("resize")
	class ResizeVolumeRequest {

		@JsonProperty("volume")
		Volume volume;

		public Volume getVolume() {
			return volume;
		}

		public void setVolume(Volume volume) {
			this.volume = volume;
		}
	}

	@JsonRootName("resize")
	class ResizeFlavorRequest {

		@JsonProperty("flavorRef")
		String flavorRef;

		public String getFlavorRef() {
			return flavorRef;
		}

		public void setFlavorRef(String flavorRef) {
			this.flavorRef = flavorRef;
		}
	}

}
