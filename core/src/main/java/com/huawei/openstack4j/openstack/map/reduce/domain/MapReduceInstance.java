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
package com.huawei.openstack4j.openstack.map.reduce.domain;

import java.util.Date;
import java.util.List;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.map.reduce.Instance;
import com.huawei.openstack4j.openstack.common.ListResult;

/**
 * Model represent attributes of Instance
 *
 * @author ekasit.kijsipongse@nectec.or.th
 */

//@JsonRootName("instance")
@JsonIgnoreProperties(ignoreUnknown=true)
public class MapReduceInstance implements Instance {

	private static final long serialVersionUID = 1L;

	@JsonProperty("instance_name")
	private String name;
	@JsonProperty("created_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createdAt;
	@JsonProperty("updated_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date updatedAt;
	@JsonProperty("instance_id")
	private String instanceId;         // Nova instance ID
	@JsonProperty("management_ip")
        private String managementIp;
	private List<String> volumes;
	@JsonProperty("internal_ip")
        private String internalIp;
	@JsonProperty("id")               // instance ID
	private String id;

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
	public Date getCreatedAt() {
		return createdAt;
	}

        /**
         * {@inheritDoc}
         */
        @Override
	public Date getUpdatedAt() {
		return updatedAt;
	}

        /**
         * {@inheritDoc}
         */
	@Override
	public String getInstanceId() {
		return instanceId;
	}

        /**
         * {@inheritDoc}
         */
	@Override
	public String getManagementIp() {
		return managementIp;
	}

        /**
         * {@inheritDoc}
         */
	@Override
	public List<String> getVolumes() {
		return volumes;
	}

        /**
         * {@inheritDoc}
         */
	@Override
	public String getInternalIp() {
		return internalIp;
	}

        /**
         * {@inheritDoc}
         */
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				   .add("instance_name",name)
                                   .add("created_at", createdAt)
                                   .add("updated_at", updatedAt)
                                   .add("instance_id", instanceId)
                                   .add("management_ip", managementIp)
				   .add("volumes", volumes)
                                   .add("internal_ip", internalIp)
                                   .add("id", id)
				   .toString();
	}

	public static class Instances extends ListResult<MapReduceInstance> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("instances")
		private List<MapReduceInstance> instances;

		public List<MapReduceInstance> value() {
			return instances;
		}
	}
}
