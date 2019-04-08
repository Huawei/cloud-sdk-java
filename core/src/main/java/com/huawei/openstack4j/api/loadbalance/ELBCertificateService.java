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
package com.huawei.openstack4j.api.loadbalance;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.loadbalance.Certificate;
import com.huawei.openstack4j.model.loadbalance.CertificateUpdate;
import com.huawei.openstack4j.openstack.loadbalance.domain.ELBCertificate.Certificates;

public interface ELBCertificateService extends RestService {
	
	Certificate create(Certificate cert);
	
	ActionResponse delete(String certificateId);
	
	Certificate update(String certificateId, CertificateUpdate cert);
	
	Certificates list();
}
