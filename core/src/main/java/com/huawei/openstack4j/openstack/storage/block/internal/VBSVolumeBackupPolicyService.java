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
package com.huawei.openstack4j.openstack.storage.block.internal;

import static com.google.common.base.Preconditions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.api.storage.BlockVolumeBackupPolicyService;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyBackupTask;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy.VolumeBackupPolicyStatus;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy.VolumeBackupScheduledPolicy;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyResource.VolumeBackupPolicyResourceActionResult;
import com.huawei.openstack4j.model.storage.block.options.BakcupTaskListOptions;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupPolicy;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupPolicyResource;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupPolicyResourceActionResult;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupScheduledPolicy;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupPolicy.VolumeBackupPolicies;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupPolicyBackupTask.VolumeBackupPolicyBackupTasks;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 21:15:20
 */
public class VBSVolumeBackupPolicyService extends BaseVolumeBackupServices implements BlockVolumeBackupPolicyService {

	/*
	 * {@inheritDoc}
	 */
	@Override
	public VolumeBackupPolicy create(VolumeBackupPolicy policy) {
		checkNotNull(policy, "parameter `policy` should not be null");
		checkState(!Strings.isNullOrEmpty(policy.getName()), "parameter `policy.name` should not be null");
		VolumeBackupScheduledPolicy scheduledPolicy = policy.getScheduledPolicy();
		checkScheduledPolicy(scheduledPolicy);
		return post(VBSVolumeBackupPolicy.class, ClientConstants.PATH_VOLUME_BACKUP_POLICY).entity(policy).execute();
	}

	/**
	 * @param scheduledPolicy
	 */
	public void checkScheduledPolicy(VolumeBackupScheduledPolicy scheduledPolicy) {
		checkNotNull(scheduledPolicy);
		checkNotNull(scheduledPolicy.getFrequency());
		checkNotNull(scheduledPolicy.getMaxBackupAmount());
		checkNotNull(scheduledPolicy.getRetainFirstBackupOfCurrentMonth());
		checkState(!Strings.isNullOrEmpty(scheduledPolicy.getStartTime()));
		checkNotNull(scheduledPolicy.getStatus());
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends VolumeBackupPolicy> list() {
		return get(VolumeBackupPolicies.class, ClientConstants.PATH_VOLUME_BACKUP_POLICY).execute().getList();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public VolumeBackupPolicy update(VolumeBackupPolicy updated) {
		checkState(!Strings.isNullOrEmpty(updated.getId()), "parameter `policy.id` should not be null");
		return put(VBSVolumeBackupPolicy.class, ClientConstants.PATH_VOLUME_BACKUP_POLICY, "/", updated.getId())
				.entity(updated).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String backupPolicyId) {
		checkState(!Strings.isNullOrEmpty(backupPolicyId), "parameter `backupPolicyId` should not be null");
		return deleteWithResponse(ClientConstants.PATH_VOLUME_BACKUP_POLICY, "/", backupPolicyId).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse execute(String backupPolicyId) {
		checkState(!Strings.isNullOrEmpty(backupPolicyId), "parameter `backupPolicyId` should not be null");
		return postWithResponse(ClientConstants.PATH_VOLUME_BACKUP_POLICY, "/", backupPolicyId, "/action").execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public VolumeBackupPolicy enable(String backupPolicyId) {
		checkState(!Strings.isNullOrEmpty(backupPolicyId), "parameter `backupPolicyId` should not be null");
		VBSVolumeBackupScheduledPolicy scheduledPolicy = VBSVolumeBackupScheduledPolicy.builder()
				.status(VolumeBackupPolicyStatus.ON).build();
		VBSVolumeBackupPolicy policy = VBSVolumeBackupPolicy.builder().id(backupPolicyId)
				.scheduledPolicy(scheduledPolicy).build();
		return update(policy);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public VolumeBackupPolicy disable(String backupPolicyId) {
		checkState(!Strings.isNullOrEmpty(backupPolicyId));
		VBSVolumeBackupScheduledPolicy scheduledPolicy = VBSVolumeBackupScheduledPolicy.builder()
				.status(VolumeBackupPolicyStatus.OFF).build();
		VBSVolumeBackupPolicy policy = VBSVolumeBackupPolicy.builder().id(backupPolicyId)
				.scheduledPolicy(scheduledPolicy).build();
		return update(policy);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public VolumeBackupPolicyResourceActionResult linkResources(String backupPolicyId, List<String> resourceIds) {
		checkState(!Strings.isNullOrEmpty(backupPolicyId), "parameter `backupPolicyId` should not be null");
		checkNotNull(resourceIds, "Parameter `resourceIds` should not be null");
		checkState(resourceIds.size() > 0, "Parameter `resourceIds` should not be empty");

		_VBSVolumeBackupPolicyResources resources = new _VBSVolumeBackupPolicyResources();
		resources.setBackupPolicyId(backupPolicyId);
		for (String resourceId : resourceIds) {
			VBSVolumeBackupPolicyResource resource = VBSVolumeBackupPolicyResource.builder().id(resourceId)
					.type("volume").build();
			resources.addResource(resource);
		}

		return post(VBSVolumeBackupPolicyResourceActionResult.class, "/backuppolicyresources").entity(resources)
				.execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public VolumeBackupPolicyResourceActionResult unlinkResources(String backupPolicyId, List<String> resourceIds) {
		checkState(!Strings.isNullOrEmpty(backupPolicyId), "Parameter `backupPolicyId` should not be empty");
		checkNotNull(resourceIds, "Parameter `resourceIds` should not be null");
		checkState(resourceIds.size() > 0, "Parameter `resourceIds` should not be empty");

		_VBSVolumeBackupPolicyResources resources = new _VBSVolumeBackupPolicyResources();
		for (String resourceId : resourceIds) {
			VBSVolumeBackupPolicyResource resource = VBSVolumeBackupPolicyResource.builder().id(resourceId).build();
			resources.addResource(resource);
		}
		return post(VBSVolumeBackupPolicyResourceActionResult.class, "/backuppolicyresources/", backupPolicyId,
				"/deleted_resources").entity(resources).execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends VolumeBackupPolicyBackupTask> tasks(String policyId, BakcupTaskListOptions options) {
		Map<String, Object> params = options != null? options.getOptions() : new HashMap<String, Object>();
		return get(VolumeBackupPolicyBackupTasks.class, ClientConstants.PATH_VOLUME_BACKUP_POLICY, "/", policyId,
				"/backuptasks").params(params).execute().getList();
	}

	@SuppressWarnings("unused")
	private static class _VBSVolumeBackupPolicyResources {

		@JsonProperty("backup_policy_id")
		String backupPolicyId;

		@JsonProperty("resources")
		private List<VBSVolumeBackupPolicyResource> resources = Lists.newArrayList();

		public String getBackupPolicyId() {
			return backupPolicyId;
		}

		/**
		 * @param resource
		 */
		public void addResource(VBSVolumeBackupPolicyResource resource) {
			resources.add(resource);
		}

		public void setBackupPolicyId(String backupPolicyId) {
			this.backupPolicyId = backupPolicyId;
		}

		public List<VBSVolumeBackupPolicyResource> getResources() {
			return resources;
		}

		public void setResources(List<VBSVolumeBackupPolicyResource> resources) {
			this.resources = resources;
		}

	}
}
