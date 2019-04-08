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
package com.huawei.openstack4j.openstack.maas.options;

import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.openstack.maas.constants.State;

public class TaskListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();

	private TaskListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}

	public static TaskListOptions create() {
		return new TaskListOptions();
	}
	
	public TaskListOptions start(int start) {
		return add("start", start);
	}
	
	public TaskListOptions limit(int limit) {
		return add("limit", limit);
	}
	
	public TaskListOptions state(State state) {
		return add("state", state.getVal());
	}
}
