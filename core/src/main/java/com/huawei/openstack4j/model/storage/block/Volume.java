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
package com.huawei.openstack4j.model.storage.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.storage.block.builder.VolumeBuilder;
import com.google.common.base.CaseFormat;

import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An OpenStack Volume
 *
 * @author Jeremy Unruh
 */
public interface Volume extends ModelEntity, Buildable<VolumeBuilder> {

	/**
	 * The current Volume Status
	 *
	 */
	public enum Status {
		AVAILABLE, ATTACHING, BACKING_UP, CREATING, DELETING, DOWNLOADING, UPLOADING, ERROR, ERROR_DELETING, ERROR_RESTORING, IN_USE, RESTORING_BACKUP, DETACHING, UNRECOGNIZED;

		@JsonValue
		public String value() {
			return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
		}

		@Override
		public String toString() {
			return value();
		}

		@JsonCreator
		public static Status fromValue(String status) {
			try {
				return valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, checkNotNull(status, "status")));
			} catch (IllegalArgumentException e) {
				return UNRECOGNIZED;
			}
		}
	}

	public enum MigrationStatus {
	    NONE, MIGRATING
	    ;

	    @JsonValue
        public String value() {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
        }

	    @Override
        public String toString() {
            return value();
        }

        @JsonCreator
        public static MigrationStatus fromValue(String migrationStatus) {
            if (migrationStatus != null)
            {
                try {
                    return valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, checkNotNull(migrationStatus, "migrationStatus")));
                } catch (IllegalArgumentException e) {
                    LoggerFactory.getLogger(MigrationStatus.class).error(e.getMessage(), e);
                }
            }
            return NONE;
        }
	}

	/**
	 * @return the identifier for the volume
	 */
	String getId();

	/**
	 * @return the name of the volume
	 */
	String getName();

	/**
	 * @return the description of the volume
	 */
	String getDescription();

	/**
	 * @return the status of the volume
	 */
	Status getStatus();

	/**
	 * @return the size in GB of the volume
	 */
	int getSize();

	/**
	 * @return the zone of availability to use
	 */
	String getZone();

	/**
	 * @return the created date of the volume
	 */
	Date getCreated();

	/**
	 * @return the type of volume
	 */
	String getVolumeType();

	/**
	 * @return the snapshot identifier
	 */
	String getSnapshotId();

	/**
	 * @return the image reference identifier (if an image was associated) otherwise null
	 */
	String getImageRef();
	
	/**
	 * @return the image reference identifier (if an image was associated) otherwise null
	 */
	Map<String, Object> getImageMetadata();

	/**
	 * @return ID of source volume to clone from
	 */
	String getSourceVolid();

	/**
	 * @return extended meta data information. key value pair of String key, String value
	 */
	Map<String, String> getMetaData();

	/**
	 * @return volume attachment data information.
	 */
	List<? extends VolumeAttachment> getAttachments();

	/**
	 * @return the status of volume migrate status, default null
	 */
	MigrationStatus getMigrateStatus();

	/**
	 * @return the tenant id
	 */
	String getTenantId();
	
	/**
	 * @return whether the volume is bootable
	 */
	boolean bootable();
	
	/**
	 * 
	 * @return whether this volume is encrypted.
	 */
	boolean encrypted();

	/**
	 *
	 * @return current back-end of the volume.
     */
	String host();

	/**
	 * @return whether the volume is shareable
	 */
	boolean multiattach();

	/**
	 * @return the backup identifier
	 */
	String getBackupId();
}
