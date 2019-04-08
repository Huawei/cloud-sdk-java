/*******************************************************************************
 * 	Copyright 2018 Huawei Technologies Co., Ltd.      
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
package com.huawei.openstack4j.model.map.reduce.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.map.reduce.Job;
import com.huawei.openstack4j.model.map.reduce.Job.JobType;

/**
 * Builder interface used for {@link Job} object.
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public interface JobBuilder extends Builder<JobBuilder, Job> {
	
    /**
     * See {@link Job#getId()}
     * 
     * @param id the id of the job
     * @return JobBuilder
     */
    JobBuilder id(String id);

    /**
     * See {@link Job#getDescription()}
     * 
     * @param description the description of the job
     * @return JobBuilder
     */
    JobBuilder description(String description);

    /**
     * See {@link Job#getType()}
     * 
     * @param type the type of the job
     * @return JobBuilder
     */
    JobBuilder type(JobType type);

    /**
     * See {@link Job#getName()}
     * 
     * @param name the name of the job
     * @return JobBuilder
     */
    JobBuilder name(String name);

    /**
     * See {@link Job#getMains()}
     * 
     * @param jobBinaryId the id of the job binary
     * @return JobBuilder
     */
    JobBuilder addMain(String jobBinaryId);

    /**
     * See {@link Job#getLibs()}
     * 
     * @param jobBinaryId the id of the job binary
     * @return JobBuilder
     */
    JobBuilder addLib(String jobBinaryId);
    
    
    /**
     * See {@link Job#getInterfaces()}
     * 
     * @param interface interface to add
     * @return JobBuilder
     */
	JobBuilder addInterface(String interface_);
    
    /**
     * See {@link Job#isPublic()}
     * 
     * @param isPublic is job public
     * @return JobBuilder
     */
    JobBuilder isPublic(boolean isPublic);
    
    /**
     * See {@link Job#isProtected()}
     * 
     * @param isProtected is job protected
     * @return JobBuilder
     */
    JobBuilder isProtect(boolean isProtected);
}
