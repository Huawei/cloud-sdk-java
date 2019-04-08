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
package com.huawei.openstack4j.api.scaling;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.ScalingPolicy;
import com.huawei.openstack4j.model.scaling.ScalingPolicyCreateUpdate;
import com.huawei.openstack4j.openstack.scaling.options.ScalingPolicyListOptions;

public interface AutoScalingPolicyService extends RestService {

	/**
	 * Create a new auto scaling configuration
	 * 
	 * @param config	model represent the attributes of auto scaling configuration creation
	 * @return the created auto-scaling config identifier
	 */
	public String create(ScalingPolicyCreateUpdate policy);

	public String update(ScalingPolicyCreateUpdate policy);

	public List<? extends ScalingPolicy> list(String groupId);

	public List<? extends ScalingPolicy> list(String groupId, ScalingPolicyListOptions options);

	public ScalingPolicy get(String policyId);

	public ActionResponse execute(String policyId);

	public ActionResponse resume(String policyId);

	public ActionResponse pause(String policyId);

	public ActionResponse delete(String policyId);
}
