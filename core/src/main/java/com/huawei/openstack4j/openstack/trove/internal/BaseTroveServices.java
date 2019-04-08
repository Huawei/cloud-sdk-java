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
package com.huawei.openstack4j.openstack.trove.internal;

import com.google.common.base.Function;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.core.transport.HttpMethod;
import com.huawei.openstack4j.openstack.internal.BaseOpenStackService;


/**
 * Base Service of Trove Service
 *
 * @author QianBiao.NG
 * @date   2017-07-31 14:30:05
 */
public class BaseTroveServices extends BaseOpenStackService {

	String CONTENT_JSON = "application/json;charset=utf-8";

	public BaseTroveServices() {
		super(ServiceType.DATABASE);
	}

	/**
	 * @param serviceType
	 */
	public BaseTroveServices(ServiceType serviceType) {
		super(serviceType);
	}

	/**
	 * @param serviceType
	 * @param endpointFunc
	 */
	public BaseTroveServices(ServiceType serviceType, Function<String, String> endpointFunc) {
		super(serviceType, endpointFunc);
	}

	/**
	 * HuaWei Relation DataBase Service(known as Trove) validate the content-type in every request
	 */
	protected <R> Invocation<R> builder(Class<R> returnType, String path, HttpMethod method) {
		// add common base path for database service
		path = "/v1.0/%(project_id)s" + path;
		
		// setup common headers for database service
		Invocation<R> invocation = super.builder(returnType, path, method);
		Config config = invocation.getRequest().getConfig();
		return invocation.header("Content-Type", CONTENT_JSON).header("X-Language", config.getLanguage());
	}

}
