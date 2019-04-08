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
package com.huawei.openstack4j.functional.mrs;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.map.reduce.options.JobExeListOptions;
import com.huawei.openstack4j.openstack.map.reduce.constants.JobState;
import com.huawei.openstack4j.openstack.map.reduce.constants.JobType;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExe;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExeCreate;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-05 15:36:39
 */
public class JobExeTest extends AbstractTest {

	@Test
	public void testGetJobExe() {
		MapReduceJobExe execution = osclient.mrs().jobExes().get("job-exe-id");
	}

	@Test
	public void testListJobExe() {
		JobExeListOptions options = JobExeListOptions.create().page(1).pageSize(20)
				.clusterId("0f4ab6b7-a723-4b6c-b326-f8a5711d365a").state(JobState.Completed);
		List<? extends MapReduceJobExe> list = osclient.mrs().jobExes().list(options);
	}

}
