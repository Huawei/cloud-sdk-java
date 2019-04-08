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
package com.huawei.openstack4j.openstack.networking.internal;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.networking.NetFloatingIPService;
import com.huawei.openstack4j.api.networking.NetworkService;
import com.huawei.openstack4j.api.networking.NetworkingService;
import com.huawei.openstack4j.api.networking.PortService;
import com.huawei.openstack4j.api.networking.RouterService;
import com.huawei.openstack4j.api.networking.SecurityGroupRuleService;
import com.huawei.openstack4j.api.networking.SecurityGroupService;
import com.huawei.openstack4j.api.networking.SubnetService;
import com.huawei.openstack4j.api.networking.ext.AgentService;
import com.huawei.openstack4j.api.networking.ext.FirewallAsService;
import com.huawei.openstack4j.api.networking.ext.IkePolicyService;
import com.huawei.openstack4j.api.networking.ext.IpsecConnectionService;
import com.huawei.openstack4j.api.networking.ext.IpsecPolicyService;
import com.huawei.openstack4j.api.networking.ext.LbaasV2Service;
import com.huawei.openstack4j.api.networking.ext.LoadBalancerService;
import com.huawei.openstack4j.api.networking.ext.NetQuotaService;
import com.huawei.openstack4j.api.networking.ext.VpnEndpointGroupService;
import com.huawei.openstack4j.api.networking.ext.VpnService;

/**
 * OpenStack Networking Operations API
 * 
 * @author Jeremy Unruh
 */
public class NetworkingServiceImpl implements NetworkingService {

    /**
     * {@inheritDoc}
     */
    @Override
    public NetworkService network() {
        return Apis.get(NetworkService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubnetService subnet() {
        return Apis.get(SubnetService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortService port() {
        return Apis.get(PortService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RouterService router() {
        return Apis.get(RouterService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetFloatingIPService floatingip() {
        return Apis.get(NetFloatingIPService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityGroupService securitygroup() {
        return Apis.get(SecurityGroupService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityGroupRuleService securityrule() {
        return Apis.get(SecurityGroupRuleService.class);
    }

    /**
     * {@inheritDoc}
     */
    /*@Override
    public NetQuotaService quotas() {
        return Apis.get(NetQuotaService.class);
    }

    *//**
     * {@inheritDoc}
     *//*
    @Override
    public LoadBalancerService loadbalancers() {
        return Apis.get(LoadBalancerService.class);
    }*/

    /**
     * {@inheritDoc}
     */
    @Override
    public LbaasV2Service lbaasV2(){
        return Apis.get(LbaasV2Service.class);
    }

	@Override
	public VpnService vpns() {
		 return Apis.get(VpnService.class);
	}

	@Override
	public IpsecConnectionService ipsecConections() {
		 return Apis.get(IpsecConnectionService.class);
	}

	@Override
	public IpsecPolicyService ipsecPolicies() {
		return Apis.get(IpsecPolicyService.class);
	}

	@Override
	public VpnEndpointGroupService vpnEndpointGroups() {
		return Apis.get(VpnEndpointGroupService.class);
	}

	@Override
	public IkePolicyService ikePolicies() {
		return Apis.get(IkePolicyService.class);
	}

    /**
     * {@inheritDoc}
     */
   /* @Override
    public FirewallAsService firewalls() {
        return Apis.get(FirewallAsService.class);
    }	

    *//**
     * {@inheritDoc}
     *//*
    @Override
    public AgentService agent() {
        return Apis.get(AgentService.class);
    }*/

}
