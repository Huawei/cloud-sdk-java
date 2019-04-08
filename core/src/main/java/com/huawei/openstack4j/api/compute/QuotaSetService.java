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
package com.huawei.openstack4j.api.compute;

import java.util.List;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.compute.Limits;
import com.huawei.openstack4j.model.compute.QuotaSet;
import com.huawei.openstack4j.model.compute.QuotaSetUpdate;
import com.huawei.openstack4j.model.compute.SimpleTenantUsage;

/**
 * OpenStack Quota-Set Operation API
 * 
 * @author Jeremy Unruh
 */
public interface QuotaSetService extends RestService {

	/**
	 * Gets the QuotaSet for the given tenant ID
	 *
	 * @param tenantId the tenant id
	 * @return the quota set
	 */
	QuotaSet get(String tenantId);
	
	/**
	 * Gets the QuotaSet for the given tenant and user ID
	 * @param tenantId the identifier for the tenant
	 * @param userId the identifier of the user
	 * @return the quota set
	 */
	QuotaSet get(String tenantId, String userId);

//	/**
//	 * Gets the Default QuotaSet for the given tenant
//	 * @param tenantId the identifier for the tenant
//	 * @return the quota set
//	 */
//	QuotaSet getDefault(String tenantId);

	/**
	 * Updates quota for a specified class
	 * 
	 * @param classId the class identifier
	 * @param qs the quota set - see {@link Builders#quotaSet()}
	 * @return the newly reflected QuotaSet
	 */
	QuotaSet updateForClass(String classId, QuotaSetUpdate qs);
	
	/**
     * Updates quota for a specified tenant
     * 
     * @param tenantId the tenant identifier
     * @param qs the quota set - see {@link Builders#quotaSet()}
     * @return the newly reflected QuotaSet
     */
	QuotaSet updateForTenant(String tenantId, QuotaSetUpdate qs);
	
	/**
	 * Accounts may be pre-configured with a set of thresholds (or limits) to manage capacity and prevent system abuse.  This call will
	 * return the current Rate and Absolute Limits.
	 * @return the system limits
	 */
	Limits limits();
	
	/**
	 * Gets tenant usage information for all tenants (os-simple-tenant-usage)
	 * 
	 * @return list of usage information for all tenants
	 */
	List<? extends SimpleTenantUsage> listTenantUsages();
	
	/**
	 * Gets the usage information for the specified tenant (os-simple-tenant-usage)
	 * 
	 * @param tenantId the tenant identifier
	 * @return Tenant Usage or null if not found
	 */
	SimpleTenantUsage getTenantUsage(String tenantId);
	
	/**
	 * Gets tenant usage information for all tenants in a period of time (os-simple-tenant-usage)
	 * @param startTime  eg:2015-05-05T23:59:59
	 * @param endTime
	 * @return list of usage information for all tenants
	 */
	List<? extends SimpleTenantUsage> listTenantUsages(String startTime, String endTime);
	
	/**
	 * Gets the usage information for the specified tenant in a period of time (os-simple-tenant-usage)
	 * 
	 * @param tenantId the tenant identifier
	 * @param startTime  eg:2015-05-05T23:59:59
	 * @param endTime
	 * @return Tenant Usage or null if not found
	 */
	SimpleTenantUsage getTenantUsage(String tenantId, String startTime, String endTime);
	
}
