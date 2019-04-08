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
package com.huawei.openstack4j.openstack.loadbalance.internal;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.loadbalance.AsyncJobService;
import com.huawei.openstack4j.api.loadbalance.ELBCertificateService;
import com.huawei.openstack4j.api.loadbalance.ELBHealthCheckService;
import com.huawei.openstack4j.api.loadbalance.ELBListenerService;
import com.huawei.openstack4j.api.loadbalance.ELBLoadBalancerService;
import com.huawei.openstack4j.api.loadbalance.ELBQuotaService;
import com.huawei.openstack4j.api.loadbalance.ELBServerService;
import com.huawei.openstack4j.api.loadbalance.ELBService;

public class ELBServiceImpl extends BaseELBServices implements ELBService {

	@Override
	public ELBLoadBalancerService loadBalancers() {
		return Apis.get(ELBLoadBalancerService.class);
	}

	@Override
	public ELBListenerService listeners() {
		return Apis.get(ELBListenerService.class);
	}

	@Override
	public ELBHealthCheckService healthchecks() {
		return Apis.get(ELBHealthCheckService.class);
	}

	@Override
	public ELBServerService servers() {
		return Apis.get(ELBServerService.class);
	}

	@Override
	public ELBQuotaService quotas() {
		return Apis.get(ELBQuotaService.class);
	}

	@Override
	public ELBCertificateService certs() {
		return Apis.get(ELBCertificateService.class);
	}

	@Override
	public AsyncJobService jobs() {
		return Apis.get(AsyncJobService.class);
	}
}
