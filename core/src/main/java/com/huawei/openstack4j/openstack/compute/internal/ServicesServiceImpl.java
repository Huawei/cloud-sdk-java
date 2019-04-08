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
package com.huawei.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.compute.ext.ServicesService;
import com.huawei.openstack4j.model.compute.ext.Service;
import com.huawei.openstack4j.openstack.compute.domain.ext.ExtService.Services;
import com.huawei.openstack4j.openstack.manila.domain.actions.ServiceAction;

/**
 * Compute Services service provides CRUD capabilities for nova service(s).
 *
 * @author Stephan Latour
 */
public class ServicesServiceImpl extends BaseComputeServices implements ServicesService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Service> list() {
        return get(Services.class, uri("/os-services")).execute().getList();
    }
    
    /**
	 * Returns list of compute services filtered by parameters.
	 * <p>Author:Wang Ting/王婷</p>
	 * @Title: list
	 * @param filteringParams
	 * @return
	 * @see com.huawei.openstack4j.api.compute.ServicesService#list(java.util.Map)
	 */
	@Override
	public List<? extends Service> list(Map<String, String> filteringParams) {
		Invocation<Services> req = get(Services.class, uri("/os-services"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}

	/**
	 * Enables a compute services.
	 * <p>Author:Wang Ting/王婷</p>
	 * @Title: enableService
	 * @param binary
	 * @param host
	 * @return
	 * @see com.huawei.openstack4j.api.compute.ServicesService#enableService(java.lang.String, java.lang.String)
	 */
	@Override
	public Service.Status enableService(String binary, String host) {
		 checkNotNull(binary);
	        checkNotNull(host);

	        return put(Service.Status.class, uri("/os-services/enable"))
	                .entity(ServiceAction.enable(binary, host))
	                .execute();
	}

	/**
	 * Disables a compute service.
	 * <p>Author:Wang Ting/王婷</p>
	 * @Title: disableService
	 * @param binary
	 * @param host
	 * @return
	 * @see com.huawei.openstack4j.api.compute.ServicesService#disableService(java.lang.String, java.lang.String)
	 */
	@Override
	public Service.Status disableService(String binary, String host) {
		checkNotNull(binary);
        checkNotNull(host);

        return put(Service.Status.class, uri("/os-services/disable"))
                .entity(ServiceAction.disable(binary, host))
                .execute();
	}
}