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
package com.huawei.openstack4j.api.workflow;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.workflow.CronTrigger;

/**
 * Service that provides CRUD operations for cron triggers.
 *
 * @author Renat Akhmerov
 */
public interface CronTriggerService extends RestService {
    /**
     * List all cron triggers with details.
     *
     * @return List of cron triggers.
     */
    List<? extends CronTrigger> list();

    /**
     * Create a new cron trigger.
     *
     * @param cronTrigger Cron trigger to create.
     * @return Created cron trigger.
     */
    CronTrigger create(CronTrigger cronTrigger);

    /**
     * Get cron trigger by its ID.
     *
     * @param id Cron trigger ID.
     * @return Cron trigger.
     */
    CronTrigger get(String id);

    /**
     * Delete cron trigger by its ID.
     *
     * @param id Cron trigger ID.
     * @return HTTP response from the server.
     */
    HttpResponse delete(String id);
}
