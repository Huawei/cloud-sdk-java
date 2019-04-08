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
package com.huawei.openstack4j.model.loadbalance;

import com.huawei.openstack4j.model.ModelEntity;

public interface LoadBalancerUpdate extends ModelEntity {
	/**
	 * @return name of load balancer
	 */
	String getName();

	/**
	 * @return description of load balancer
	 */
	String getDescription();

	/**
	 * @return bandwidth of load balancer
	 */
	Integer getBandwidth();

	/**
	 * @return administration state of load balancer
	 */
	Integer getAdminStateUp();
}
