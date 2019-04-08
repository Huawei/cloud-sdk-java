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
package com.huawei.openstack4j.openstack.cloud.trace.v2.options;

import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.openstack.cloud.trace.constants.TraceStatus;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-13 10:21:04
 */
public class TraceListOptions {

	private Map<String, Object> queryParams = Maps.newHashMap();
	
	protected TraceListOptions() {
		
	}
	
	public TraceListOptions id(String traceId) {
		return add("trace_id", traceId);
	}
	
	public TraceListOptions name(String traceName) {
		return add("trace_name", traceName);
	}
	
	public TraceListOptions status(TraceStatus traceStatus) {
		return add("trace_status", traceStatus.value());
	}
	
	public TraceListOptions serviceType(String serviceType) {
		return add("service_type", serviceType);
	}
	
	public TraceListOptions resourceType(String resourceType) {
		return add("resource_type", resourceType);
	}
	
	public TraceListOptions resourceId(String resourceId) {
		return add("resource_id", resourceId);
	}
	
	public TraceListOptions resourceName(String resourceName) {
		return add("resource_name", resourceName);
	}
	
	public TraceListOptions from(Date from) {
		return add("from", from.getTime());
	}
	
	public TraceListOptions to(Date to) {
		return add("to", to.getTime());
	}
	
	public TraceListOptions user(String user) {
		return add("user", user);
	}
	
	public TraceListOptions limit(Integer limit) {
		return add("limit", limit);
	}
	
	public TraceListOptions marker(String marker) {
		return add("next", marker);
	}
	
	/**
	 * alternative to {@link #marker(String)}
	 * @param next
	 * @return
	 */
	public TraceListOptions next(String next) {
		return add("next", next);
	}
	
	public TraceListOptions sequence(String sequence) {
		return add("sequence", sequence);
	}

	private TraceListOptions add(String param, Object value) {
		if (value != null)
			this.queryParams.put(param, value);
		return this;
	}

	public Map<String, Object> getOptions() {
		return queryParams;
	}

	public static TraceListOptions create() {
		return new TraceListOptions();
	}

}
