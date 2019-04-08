 /*******************************************************************************
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
 *******************************************************************************/
package com.huawei.openstack4j.openstack.storage.block.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupJob;
import com.huawei.openstack4j.openstack.common.DateTimeUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 10:44:01
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VBSVolumeBackupJob implements AsyncVolumeBackupJob {

	private static final long serialVersionUID = -9174668588171960734L;

	@JsonProperty("job_id")
	String id;

	@JsonProperty("job_type")
	String type;

	@JsonProperty("entities")
	HashMap<String, Object> entities;

	@JsonProperty("sub_jobs")
	List<AsyncVolumeBackupJob> subJobs;

	Status status;

	@JsonProperty("begin_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS_SSSZ)
	Date beginTime;

	@JsonProperty("end_time")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS_SSSZ)
	Date endTime;

	@JsonProperty("error_code")
	String errorCode;

	@JsonProperty("fail_reason")
	String failReason;

}
