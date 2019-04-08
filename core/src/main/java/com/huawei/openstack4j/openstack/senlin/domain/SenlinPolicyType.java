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
package com.huawei.openstack4j.openstack.senlin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.senlin.PolicyType;
import com.huawei.openstack4j.openstack.common.ListResult;

import java.util.List;
import java.util.Map;

/**
 * This is a model of a senlinPolicy_type. It uses Jackson annotations for
 * (de)serialization into JSON format
 * 
 * @author lion
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("policy_type")
public class SenlinPolicyType implements PolicyType {
	private static final long serialVersionUID = -8893826725585696810L;

	@JsonProperty("name")
	private String name;
	@JsonProperty("schema")
	private Map<String, Object> schema;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Map<String, Object> getSchema() {
		return schema;
	}

	@Override
	public String toString() {
		return "SenlinPolicyType{" +
				"name='" + name + '\'' +
				", schema=" + schema +
				'}';
	}

	/**
	 * An inner class for representing lists of senlinPolicy_type
	 * 
	 * @author lion
	 * 
	 */
	public static class PolicyType extends ListResult<SenlinPolicyType> {
		private static final long serialVersionUID = -4755855096962007407L;

		@JsonProperty("policy_types")
		private List<SenlinPolicyType> list;

		protected List<SenlinPolicyType> value() {
			return list;
		}
	}
}
