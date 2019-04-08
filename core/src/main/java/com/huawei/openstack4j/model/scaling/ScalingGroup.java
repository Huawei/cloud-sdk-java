/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package com.huawei.openstack4j.model.scaling;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;

import com.google.common.base.Strings;

public interface ScalingGroup extends ModelEntity {

	public enum ScalingGroupStatus {
		INSERVICE, PAUSED, ERROR;

		@JsonCreator
		public ScalingGroupStatus forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (ScalingGroupStatus status : ScalingGroupStatus.values()) {
					if (status.name().equalsIgnoreCase(value)) {
						return status;
					}
				}
			}
			return null;
		}
	}

	public enum HealthPeriodicAuditMethod {
		ELB_AUDIT, NOVA_AUDIT;

		@JsonCreator
		public HealthPeriodicAuditMethod forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (HealthPeriodicAuditMethod method : HealthPeriodicAuditMethod.values()) {
					if (method.name().equalsIgnoreCase(value)) {
						return method;
					}
				}
			}
			return null;
		}
	}

	public enum InstanceTerminatePolicy {
		OLD_CONFIG_OLD_INSTANCE, OLD_CONFIG_NEW_INSTANCE, OLD_INSTANCE, NEW_INSTANCE;

		@JsonCreator
		public InstanceTerminatePolicy forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (InstanceTerminatePolicy policy : InstanceTerminatePolicy.values()) {
					if (policy.name().equalsIgnoreCase(value)) {
						return policy;
					}
				}
			}
			return null;
		}
	}

	/**
	 * @return the id for the scaling group
	 */
	String getGroupId();

	/**
	 * @return the name for the scaling group
	 */
	String getGroupName();

	/**
	 * 
	 * @return scaling group status
	 */
	ScalingGroupStatus getGroupStatus();

	/**
	 * 
	 * @return scaling configuration id
	 */
	String getConfigId();

	/**
	 * 
	 * @return scaling configuration name
	 */
	String getConfigName();

	/**
	 * 
	 * @return current instance number
	 */
	Integer getCurrentInstanceNumber();

	/**
	 * 
	 * @return desire instance number
	 */
	Integer getDesireInstanceNumber();

	/**
	 * 
	 * @return minimal instance number
	 */
	Integer getMinInstanceNumber();

	/**
	 * 
	 * @return maximal instance number
	 */
	Integer getMaxInstanceNumber();

	/**
	 * 
	 * @return cool down time
	 */
	Integer getCoolDownTime();

	/**
	 * 
	 * @return load balance listener id
	 */
	String getLbListenerId();

	/**
	 * 
	 * @return availability zones
	 */
	List<String> getAvailabilityZones();

	/**
	 * 
	 * @return networks information
	 */
	List<IdResourceEntity> getNetworks();

	/**
	 * 
	 * @return security groups information
	 */
	List<IdResourceEntity> getSecurityGroups();

	/**
	 * 
	 * @return scaling group creation time
	 */
	Date getCreateTime();

	/**
	 * 
	 * @return vpc id
	 */
	String getVpcId();

	/**
	 * 
	 * @return scaling group detail
	 */
	String getDetail();

	/**
	 * 
	 * @return Is group scale
	 */
	Boolean getIsScaling();

	/**
	 * 
	 * @return health periodic audit method
	 */
	HealthPeriodicAuditMethod getHealthPeriodicAuditMethod();

	/**
	 * 
	 * @return health periodic audit time
	 */
	Integer getHealthPeriodicAuditTime();

	/**
	 * 
	 * @return instance terminate policy
	 */
	InstanceTerminatePolicy getInstanceTerminatePolicy();

	/**
	 * 
	 * @return notification method
	 */
	List<String> getNotifications();

	/**
	 * 
	 * @return whether delete server's ip
	 */
	Boolean getDeletePublicip();
}
