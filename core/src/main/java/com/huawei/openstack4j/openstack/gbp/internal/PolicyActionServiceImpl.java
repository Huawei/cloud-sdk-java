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
package com.huawei.openstack4j.openstack.gbp.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.gbp.PolicyActionService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.PolicyAction;
import com.huawei.openstack4j.model.gbp.PolicyActionUpdate;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyAction;
import com.huawei.openstack4j.openstack.gbp.domain.GbpPolicyAction.PolicyActions;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 * Policy Action API Implementation
 * 
 * @author vinod borole
 */
public class PolicyActionServiceImpl extends BaseNetworkingServices implements PolicyActionService {

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<? extends PolicyAction> list() {
        return get(PolicyActions.class, uri("/grouppolicy/policy_actions")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends PolicyAction> list(Map<String, String> filteringParams) {
        Invocation<PolicyActions> policyactionInvocation = buildInvocation(filteringParams);
        return policyactionInvocation.execute().getList();
    }
    
    private Invocation<PolicyActions> buildInvocation(Map<String, String> filteringParams) {
        Invocation<PolicyActions> policyactionInvocation = get(PolicyActions.class, "/grouppolicy/policy_actions");
        if (filteringParams == null) { 
            return policyactionInvocation;
        } 
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                policyactionInvocation = policyactionInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return policyactionInvocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyAction get(String id) {
        checkNotNull(id);
        return get(GbpPolicyAction.class, uri("/grouppolicy/policy_actions/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/grouppolicy/policy_actions/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyAction create(PolicyAction policyAction) {
        return post(GbpPolicyAction.class, uri("/grouppolicy/policy_actions")).entity(policyAction).execute();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public PolicyAction update(String policyActionId, PolicyActionUpdate policyAction) {
        checkNotNull(policyActionId);
        checkNotNull(policyAction);
        return put(GbpPolicyAction.class, uri("/grouppolicy/policy_actions/%s", policyActionId)).entity(policyAction).execute();
    }

}
