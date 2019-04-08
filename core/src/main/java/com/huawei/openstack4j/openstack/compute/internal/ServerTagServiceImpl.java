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
package com.huawei.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.openstack4j.api.compute.ServerTagService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerTag;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;

public class ServerTagServiceImpl extends BaseComputeServices implements ServerTagService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ServerTagServiceImpl.class);

	@Override
	public NovaServerTag list(String serverId) {
		checkNotNull(serverId);
		return this.get(NovaServerTag.class, this.uri("/servers/%s/tags", serverId)).execute();
	}

	@Override
	public NovaServerTag addTags(String serverId, NovaServerTag tags) {
		checkNotNull(serverId);
		checkNotNull(tags);
		return this.put(NovaServerTag.class, this.uri("/servers/%s/tags", serverId)).entity(tags).execute();
	}

	@Override
	public ActionResponse deleteAll(String serverId) {
		checkNotNull(serverId);
		return ToActionResponseFunction.INSTANCE.apply(
				this.delete(Void.class, this.uri("/servers/%s/tags", serverId)).executeWithResponse());
	}

	@Override
	public ActionResponse delete(String serverId, String tag) {
		checkNotNull(serverId);
		checkNotNull(tag);
		return ToActionResponseFunction.INSTANCE.apply(
				this.delete(Void.class, this.uri("/servers/%s/tags/%s", serverId, tag)).executeWithResponse());
	}

	@Override
	public ActionResponse check(String serverId, String tag) {
		checkNotNull(serverId);
		checkNotNull(tag);
		return ToActionResponseFunction.INSTANCE.apply(
				this.get(Void.class, this.uri("/servers/%s/tags/%s", serverId, tag)).executeWithResponse());
	}

	@Override
	public ActionResponse addSingle(String serverId, String tag) {
		checkNotNull(serverId);
		checkNotNull(tag);
		return ToActionResponseFunction.INSTANCE.apply(
				this.put(ActionResponse.class, this.uri("/servers/%s/tags/%s", serverId, tag)).executeWithResponse());
	}

}
