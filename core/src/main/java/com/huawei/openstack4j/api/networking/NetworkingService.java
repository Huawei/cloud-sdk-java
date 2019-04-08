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
package com.huawei.openstack4j.api.networking;

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
import com.huawei.openstack4j.common.RestService;

/**
 * OpenStack Networking Operations API
 * 
 * @author Jeremy Unruh
 */
public interface NetworkingService extends RestService {

    /**
     * @return the Network Service API
     */
    NetworkService network();

    /**
     * @return the Subnet Service API
     */
    SubnetService subnet();

    /**
     * @return the Port Service API
     */
    PortService port();

    /**
     * @return the Router Service API
     */
    RouterService router();

    /**
     * @return the FloatingIP Service API
     */
    NetFloatingIPService floatingip();

    /**
     *
     * @return the Security Group Service API
     */
    SecurityGroupService securitygroup();

    /**
     *
     * @return the Security Group Rule Service API
     */
    SecurityGroupRuleService securityrule();

    /**
     * @return the network quota service
     */
    //NetQuotaService quotas();
   
    /**
     * @return the LBaaS service
     */
    //LoadBalancerService loadbalancers();
    
    /**
     * 
     * @return the Neutron agent API
     */
    //AgentService agent();

    /**
     * @return the LBaaS V2 service
     */
   LbaasV2Service lbaasV2();

    /**
     * <p>OpenStack Firewall As a Service <code>(FwaaS) : Firewall</code> Operations API</p>
     * 
     * @return the FwaaS service
     */
   // FirewallAsService firewalls();
   
   /**
    * 
    * @return the  vpn service
    */
   VpnService vpns();
   
   /**
    * 
    * @return	the  vpn conection service
    */
   IpsecConnectionService ipsecConections();
   /**
    * 
    * @return	the  lpsecPolicy service
    */
   IpsecPolicyService ipsecPolicies();
   /**
    * 
    * @return	the  vpnEndpointGroups service
    */
   VpnEndpointGroupService   vpnEndpointGroups();
   /**
    * 
    * @return	the  ikePolicies service
    */
   IkePolicyService  ikePolicies();
}
