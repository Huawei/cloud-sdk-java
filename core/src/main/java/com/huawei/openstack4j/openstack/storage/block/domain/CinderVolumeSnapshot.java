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
package com.huawei.openstack4j.openstack.storage.block.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.huawei.openstack4j.model.storage.block.VolumeSnapshot;
import com.huawei.openstack4j.model.storage.block.Volume.Status;
import com.huawei.openstack4j.model.storage.block.builder.VolumeSnapshotBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * An OpenStack Volume Snapshot which is a point-in-time copy of a volume.
 *
 * @author Jeremy Unruh
 */
@JsonRootName("snapshot")
public class CinderVolumeSnapshot implements VolumeSnapshot {

	private static final long serialVersionUID = 1L;

	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("volume_id")
	private String volumeId;
	private Status status;
	@JsonInclude(Include.NON_DEFAULT)
	@JsonProperty("size")
	private Integer size;
	@JsonProperty("created_at")
	private Date created;
	@JsonProperty
	private Boolean force;
	@JsonProperty("metadata")
	private Map<String, String> metadata;
	@JsonProperty("updated_at")
	private String updatedAt;
	@JsonProperty("os-extended-snapshot-attributes:project_id")
	private String projectId;
	@JsonProperty("os-extended-snapshot-attributes:progress")
	private String progress;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VolumeSnapshotBuilder toBuilder() {
		return new ConcreteVolumeSnapshotBuilder(this);
	}

	/**
	 * @return a new Volume Snapshot builder
	 */
	public static VolumeSnapshotBuilder builder() {
		return new ConcreteVolumeSnapshotBuilder();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getVolumeId() {
		return volumeId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return (size != null) ? size : 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getCreated() {
		return created;
	}

	/**
     * {@inheritDoc}
     */
	@JsonIgnore
	@Override
	public Map<String, String> getMetaData() {
	    return metadata;
	}

	public String getUpdatedAt()
	{
		return updatedAt;
	}

	public String getProjectId()
	{
		return projectId;
	}

	public String getProgress()
	{
		return progress;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				     .add("id", id).add("name", name).add("description", description).add("volumeId", volumeId)
				     .add("status", status).add("created", created).add("force", force).add("size", size).add("metadata", metadata)
					 .add("projectId",projectId).add("progress",progress).add("updatedAt",updatedAt)
					 .toString();
	}

	public static class VolumeSnapshots extends ListResult<CinderVolumeSnapshot> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("snapshots")
		private List<CinderVolumeSnapshot> snapshots;

		@Override
		protected List<CinderVolumeSnapshot> value() {
			return snapshots;
		}
	}

	public static class ConcreteVolumeSnapshotBuilder implements VolumeSnapshotBuilder {

		private CinderVolumeSnapshot m;

		ConcreteVolumeSnapshotBuilder() {
			this(new CinderVolumeSnapshot());
		}

		ConcreteVolumeSnapshotBuilder(CinderVolumeSnapshot m) {
			this.m = m;
		}

		@Override
		public VolumeSnapshotBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		public VolumeSnapshotBuilder description(String description) {
			m.description = description;
			return this;
		}

		@Override
		public VolumeSnapshotBuilder volume(String volumeId) {
			m.volumeId = volumeId;
			return this;
		}

		@Override
		public VolumeSnapshotBuilder force(boolean force) {
			m.force = force;
			return this;
		}

		@Override
		public VolumeSnapshotBuilder metadata(Map<String, String> metadata) {
		    m.metadata = metadata;
		    return this;
		}

		@Override
		public VolumeSnapshot build() {
			return m;
		}

		@Override
		public VolumeSnapshotBuilder from(VolumeSnapshot in) {
			m = (CinderVolumeSnapshot) in;
			return this;
		}

	}

}
