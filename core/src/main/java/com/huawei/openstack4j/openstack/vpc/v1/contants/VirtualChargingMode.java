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
package com.huawei.openstack4j.openstack.vpc.v1.contants;

import com.google.common.base.Strings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Model for Server Bandwidth Charging Mode 
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum VirtualChargingMode {
	
	TRAFFIC("traffic"), BANDWIDTH("bandwidth");

	private String val;

	private VirtualChargingMode(String val) {
		this.val = val;
	}

	@JsonValue
	public String getVal() {
		return this.val;
	}

	@JsonCreator
	public VirtualChargingMode forValue(String value) {
		if (!Strings.isNullOrEmpty(value)) {
			for (VirtualChargingMode mode : VirtualChargingMode.values()) {
				if (mode.getVal().equalsIgnoreCase(value)) {
					return mode;
				}
			}
		}
		return null;
	}
}
