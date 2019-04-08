/*******************************************************************************
 * 	Copyright 2017 HuaWei TLD and OTC                                          
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
package com.huawei.openstack4j.openstack.antiddos.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.antiddos.constants.NetworkType;
import com.huawei.openstack4j.openstack.antiddos.constants.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AntiDDoSStatus implements ModelEntity {

	private static final long serialVersionUID = -2054296701363380933L;

	/**
	 * elastic ip number
	 */
	private Integer total;

	/**
	 * ddos status
	 */
	private List<DDoSStatus> ddosStatus;

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DDoSStatus {

		/**
		 * floating ip address
		 */
		@JsonProperty("floating_ip_address")
		private String floatingIpAddress;

		/**
		 * floating ip id
		 */
		@JsonProperty("floating_ip_id")
		private String floatingIpId;

		/**
		 * network type
		 */
		@JsonProperty("network_type")
		private NetworkType networkType;

		/**
		 * status
		 */
		private Status status;
	}
}
