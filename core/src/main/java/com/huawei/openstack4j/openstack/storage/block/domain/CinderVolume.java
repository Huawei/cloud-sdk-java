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
package com.huawei.openstack4j.openstack.storage.block.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.storage.block.Volume;
import com.huawei.openstack4j.model.storage.block.VolumeAttachment;
import com.huawei.openstack4j.model.storage.block.builder.VolumeBuilder;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * An OpenStack Volume
 *
 * @author Jeremy Unruh
 */
@JsonRootName("volume")
public class CinderVolume implements Volume {

	private static final long serialVersionUID = 1L;

	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	private Status status;
	@JsonInclude(Include.NON_DEFAULT)
	@JsonProperty("size")
	private Integer size;
	@JsonProperty("availability_zone")
	private String zone;
	@JsonProperty("created_at")
	private Date created;
	@JsonProperty("volume_type")
	private String volumeType;
	@JsonProperty("imageRef")
	private String imageRef;
	@JsonProperty("source_volid")
	private String sourceVolid;
	@JsonProperty("snapshot_id")
	private String snapshotId;
	@JsonProperty("metadata")
	private Map<String, String> metadata;
	@JsonProperty("bootable")
	private Boolean bootable;
	@JsonProperty("attachments")
	private List<CinderVolumeAttachment> attachments;
	@JsonProperty("image_id")
	private String imageId;
	@JsonProperty("volume_image_metadata")
	private Map<String, Object> imageMetadata;
	@JsonProperty("os-vol-mig-status-attr:migstat")
	private MigrationStatus migrateStatus;
	@JsonProperty("os-vol-tenant-attr:tenant_id")
	private String tenantId;
	@JsonProperty("encrypted")
	private Boolean encrypted;
	@JsonProperty("os-vol-host-attr:host")
	private String host;
	@JsonProperty("backup_id")
	private String backupId;
	@JsonProperty("multiattach")
	private Boolean multiattach;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VolumeBuilder toBuilder() {
		return new ConcreteVolumeBuilder(this);
	}

	/**
	 * @return the Volume Builder
	 */
	public static VolumeBuilder builder() {
		return new ConcreteVolumeBuilder();
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
	public Status getStatus() {
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return (size == null) ? 0 : size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getZone() {
		return zone;
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
	@Override
	public String getVolumeType() {
		return volumeType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSnapshotId() {
		return snapshotId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MigrationStatus getMigrateStatus() {
		return migrateStatus;
	}

	@Override
	public Map<String, Object> getImageMetadata() {
		return imageMetadata;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getImageRef() {
	    if (imageRef != null)
	        return imageRef;

	    // Depending on whether this is a Listing or a direct Get the information is different so we are smart
	    // about returning the proper imageId if applicable
	    if (imageId == null && imageMetadata != null && imageMetadata.containsKey("image_id"))
	        imageId = String.valueOf(imageMetadata.get("image_id"));

	    return imageId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSourceVolid() {
		return sourceVolid;
	}

	/**
	 * {@inheritDoc}
	 */
	@JsonIgnore
	@Override
	public Map<String, String> getMetaData() {
		return metadata;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends VolumeAttachment> getAttachments() {
		return attachments;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean bootable(){
		return bootable;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean encrypted(){
		return encrypted;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String host() { return host; }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean multiattach()
	{
		return multiattach;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getBackupId()
	{
		return backupId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				     .add("id", id).add("name", name).add("description", description)
				     .add("status", status).add("size", size).add("zone", zone).add("created", created)
				     .add("volumeType", volumeType).add("imageRef", getImageRef())
				     .add("sourceVolid", sourceVolid).add("snapshotId", snapshotId).add("metadata", metadata)
				     .add("bootable", bootable).add("imageMetadata", imageMetadata)
				     .add("backupId",backupId).add("multiattach",multiattach)
				     .toString();
	}

	public static class Volumes extends ListResult<CinderVolume> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("volumes")
		private List<CinderVolume> volumes;

		@Override
		protected List<CinderVolume> value() {
			return volumes;
		}
	}

	public static class ConcreteVolumeBuilder implements VolumeBuilder {

		private CinderVolume m;

		ConcreteVolumeBuilder() {
			this(new CinderVolume());
		}

		ConcreteVolumeBuilder(CinderVolume m) {
			this.m = m;
		}

		@Override
		public VolumeBuilder name(String name) {
			m.name = name;
			return this;
		}

		@Override
		public VolumeBuilder description(String description) {
			m.description = description;
			return this;
		}

		@Override
		public VolumeBuilder source_volid(String uuid) {
			m.sourceVolid = uuid;
			return this;
		}

		@Override
		public VolumeBuilder snapshot(String snapshotId) {
			m.snapshotId = snapshotId;
			return this;
		}

		@Override
		public VolumeBuilder imageRef(String imageRef) {
			m.imageRef = imageRef;
			return this;
		}

		@Override
		public VolumeBuilder size(int size) {
			m.size = size;
			return this;
		}

		@Override
		public VolumeBuilder volumeType(String volumeType) {
			m.volumeType = volumeType;
			return this;
		}

		@Override
		public VolumeBuilder bootable(boolean isBootable) {
			m.bootable = isBootable;
			return this;
		}

		@Override
		public VolumeBuilder metadata(Map<String, String> metadata) {
			m.metadata = metadata;
			return this;
		}

		@Override
		public VolumeBuilder imageMetadata(Map<String, Object> imageMetadata) {
			m.imageMetadata = imageMetadata;
			return this;
		}

		@Override
		public Volume build() {
			return m;
		}

		@Override
		public VolumeBuilder from(Volume in) {
			m = (CinderVolume) in;
			return this;
		}

        @Override
        public VolumeBuilder zone(String zone) {
            m.zone = zone;
            return this;
        }

		@Override
		public VolumeBuilder multiattach(Boolean multiattach)
		{
			m.multiattach = multiattach;
			return this;
		}

		@Override
		public VolumeBuilder backupId(String backupId)
		{
			m.backupId = backupId;
			return this;
		}
	}
}
